package com.akoufatzis.weatherapp.injection.scopes;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by alexk on 05/05/16.
 */

@Scope
@Retention(RUNTIME)
public @interface PerApplication {
}
