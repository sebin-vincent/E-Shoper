package com.example.Authserver.config;

import com.example.Authserver.repository.UserRepository;
import com.mongodb.client.MongoClient;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Collections;
import java.util.Map;

@Configuration
public class OAuth2Configuration extends AuthorizationServerConfigurerAdapter {

    @Value("${marketplace.security-config.KEY_STORE_FILE}")
    private String KEY_STORE_FILE;

    @Value("${marketplace.security-config.KEY_STORE_PASSWORD}")
    private String KEY_STORE_PASSWORD;

    @Value("${marketplace.security-config.KEY_ALIAS}")
    private String KEY_ALIAS;

    @Value("${marketplace.security-config.JWK_KID}")
    private String JWK_KID;

    @Value("${marketplace.security-config.clientId}")
    private String CLIENT_ID;

    @Value("${marketplace.security-config.clientSecret}")
    private String CLIENT_SECRET;

    @Value("${marketplace.security-config.accessValidity}")
    private int ACCESS_VALIDITY;

    @Value("${marketplace.security-config.refreshValidity}")
    private int REFRESH_VALIDITY;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MongoClient mongoClient;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient(CLIENT_ID) //if we want to make in memmory client details
                .secret(CLIENT_SECRET).scopes("read","write")
                .authorizedGrantTypes("password","refresh_token").accessTokenValiditySeconds(ACCESS_VALIDITY)
                .refreshTokenValiditySeconds(REFRESH_VALIDITY);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer configure) {
        configure
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
                .accessTokenConverter(accessTokenConverter());
        configure.userDetailsService(userDetailsService);

        //Custom endpoint for token generation
        configure.pathMapping("/oauth/token", "/signin");

        configure.exceptionTranslator(exception -> {
            if (exception instanceof OAuth2Exception) {
                OAuth2Exception oAuth2Exception = (OAuth2Exception) exception;
                return ResponseEntity
                        .status(oAuth2Exception.getHttpErrorCode())
                        .body(new CustomOauthException(oAuth2Exception.getMessage()));
            } else {
                throw exception;
            }
        });


    }

    @Bean
    public KeyPair keyPair() {
        ClassPathResource ksFile = new ClassPathResource(KEY_STORE_FILE);
        KeyStoreKeyFactory ksFactory = new KeyStoreKeyFactory(ksFile, KEY_STORE_PASSWORD.toCharArray());
        return ksFactory.getKeyPair(KEY_ALIAS);
    }

    @Bean
    public JWKSet jwkSet() {
        RSAKey.Builder builder = new RSAKey.Builder((RSAPublicKey) keyPair().getPublic()).keyUse(KeyUse.SIGNATURE)
                .algorithm(JWSAlgorithm.RS256)
                .keyID(JWK_KID);
        return new JWKSet(builder.build());
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        Map<String, String> customHeaders = Collections.singletonMap("kid", JWK_KID);
        return new CustomTokenConverter(customHeaders, keyPair());
    }



}
