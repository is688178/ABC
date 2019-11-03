package com.example.abc.config

import android.app.Application
import com.parse.Parse

private const val APPLICATION_ID = "S7UgQLQfFGefiICqEseHlK5Gul5ryYT4vmQHlOTw"
private const val CLIENT_KEY = "z6k4Ay9nwrsOxg1KpJIHszcWeVv9s9VdMlatHFOj"
private const val SERVER_URL = "https://parseapi.back4app.com/"

class ParseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Configure parse using parse server url, app id and client Key
        // Look at https://docs.parseplatform.org for more details
        configureParse {
            applicationId(APPLICATION_ID)
            clientKey(CLIENT_KEY)
            server(SERVER_URL)
        }
    }

    /** Initialize Parse using the provided server configurations
     * @param builder a lambda function with a [Parse.Configuration.Builder] as its context or Receiver
     */
    private inline fun configureParse(builder: Parse.Configuration.Builder.() -> Unit) {

        return Parse.initialize(Parse.Configuration.Builder(this).apply(builder).build())
    }
}