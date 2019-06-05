# API key

## Get the SHA-1 Fingerprint

Enter the following command in the Terminal of your project in AndroidStudio:
- on Windows: `keytool -list -v -alias androiddebugkey -keystore  C:\Users\<Your Username>\.android\debug.keystore -storepass android -keypass android`
- on Mac: `keytool -list -v -alias androiddebugkey -keystore /Users/<Your Username>/.android/debug.keystore -storepass android -keypass android`
- on Linux: `keytool -list -v -alias androiddebugkey -keystore /home/<Your Username>/.android/debug.keystore -storepass android -keypass android`

For Release mode change the value of "alias" and of "keystore" to the file, where your key is located:
```
keytool -list -v -keystore <Your Keystore> -alias <Your Alias>
```
Example:
- on Windows: `keytool -list -v -keystore C:\Users\<Your Username>\example.jks -alias example`
- on Mac: `keytool -list -v -keystore /Users/<Your Username>/example.jks -alias example`
- on Linux: `keytool -list -v -keystore /home/<Your Username>/example.jks -alias example`
 

## Get the required API key

1.	Go Visit [Google Cloud Console](https://console.cloud.google.com).
2.	Sign in with your google account.
3.	Create a new project.
4.	Enter a name and an id for your project and hit the "CREATE"-Button.
5.	Click on your project.
6. 	Click on the Navigation Menu (upper left corner) 
7.	Go to API & Services >> Library
8.	Search for the "Places API" and enable it
9.	Go to API & Services >> Credentials
10.	Click on "Create Credentials" >> "API key"
11.	Click on "RESTRICT KEY"
12.	Under Application restrictions select the following option: Android apps
13.	Under "Package name" enter the following package: `com.devonfw.mythaistar`
14.	Under "SHA-1 certificate fingerprint" enter your SHA-1 Fingerprint
15.	Under "API restrictions" restrict your API key to the Places API
16.	Hit the "SAVE"-Button

Your API key is now ready to use.
