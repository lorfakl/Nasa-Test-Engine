package main.java.nasaTestSuite;

public class MyXPath {
    //screens
    public static String signInRegisterScreen = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView";
    public static String registerScreen = "	/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View[2]/android.widget.Button[2]"; //register button
    public static String verifyEmailScreen = ""; //leads back to sign in with back button
    public static String signInTypeScreen = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View[5]/android.widget.Button"; //sign in button

    //updateScreen
    public static String updateButton = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.Button";
    //signInRegisterScreen
    public static String signInOne = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.Button[1]";
    //signInScreen
    public static String emailField = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]/android.widget.EditText";
    public static String passField = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.widget.EditText";
    public static String signInTwo = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View[5]/android.widget.Button";

    //firstApplianceScreen
    public static String addAppliance = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[4]/android.view.View[2]/android.widget.Button";

    //applianceOneControlHUMID
    public static String targHumidity = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]/android.view.View/android.view.View[1]/android.widget.ListView/android.view.View[12]/android.view.View[1]";
    public static String contHumidity = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]/android.view.View/android.view.View[1]/android.widget.ListView/android.view.View[1]";
    public static String currentHumidityText = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]/android.view.View/android.view.View[1]/android.view.View[1]";
    public static String humidPlusButton = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]/android.view.View/android.view.View[1]/android.view.View[3]";
    public static String humidMinusButton = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]/android.view.View/android.view.View[1]/android.view.View[2]";
    public static String dSpeedUp = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]/android.view.View/android.view.View[2]/android.view.View[3]";
    public static String dSpeedDown = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]/android.view.View/android.view.View[2]/android.view.View[2]";
    public static String thinking = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[4]/android.view.View[1]/android.view.View[2]";//this should help prevent us from clicking while thinking



}
