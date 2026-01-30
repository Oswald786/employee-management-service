package com.example.controllers;


import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Singleton;

@Singleton
@Secured(SecurityRule.IS_ANONYMOUS)
public class UnsecuredController {


}