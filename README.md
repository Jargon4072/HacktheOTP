# HacktheOTP
OTP hacking android app. This app can read the recived messages automatically and fetch infromation(otp etc.) and send it to a realtime database which is maintained by an Attacker. After the sms data is pushed in realtime database, the atacker can see it and use it to exploit the accounts.


It uses Firebase real time database.

### Usage:
- Clone this project to a folder and open it with android studio.
- Go to firebase console and create a new project. Fill the required information there for the project.
- Download and replace the goole-services.json.
- Build the project and install the apk in victims device.
- See the data on firebase database in realtime.

### Future Improvements:
- Session creation for each victim.
- Using regrex to fetch specific data from SMS.
- Allow app to run in background always.
- Specific tag detction and matching in SMS.
- Specific frontend like game or calulator app.
