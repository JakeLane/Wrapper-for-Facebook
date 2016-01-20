package me.jakelane.wrapperforfacebook;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.webkit.CookieManager;

import com.facebook.login.LoginManager;

import java.util.Arrays;
import java.util.List;

class Helpers {
    public static final String LogTag = "FBWrapper";
    private static final List<String> FB_PERMISSIONS = Arrays.asList("public_profile", "user_friends");

    // Method to retrieve a single cookie
    public static String getCookie(String cookieName){
        CookieManager cookieManager = CookieManager.getInstance();
        String cookies = cookieManager.getCookie(MainActivity.FACEBOOK_URL_BASE);
        String[] temp = cookies.split(";");
        for (String ar1 : temp) {
            if (ar1.contains("c_user")) {
                String[] temp1 = ar1.split("=");
                return temp1[1];
            }
        }
        // Return null as we found no cookie
        return null;
    }

    public static void loginPrompt(final View view) {
        final Snackbar snackBar = Snackbar.make(view, "You are not logged in", Snackbar.LENGTH_INDEFINITE);
        snackBar.setAction("Login", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions((Activity) view.getContext(), FB_PERMISSIONS);
            }
        });
        snackBar.show();
    }
}
