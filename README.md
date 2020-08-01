# Fantasy Football Email Service

This project is a simple Java project that uses the Web Crawling library JSoup to gather Fantasy Football data and email it.

For my purposes I set up a Cron Job that runs every Tuesday at 8am. This Cron job runs the main application which gets the Fantasy Draft Player Rankings and emails it to myself.

## Future Features

* Email Weekly Fantasy Stats from the best players organized by position.

* Build server to automate the email process so more people can use the service.

## Getting your Send Grid API Key

1. Go to [Send Grid](https://sendgrid.com)
2. Set up a new account
3. On the dashboard page, find `Settings` and click on `API Keys`
4. Click on `Create API Key`
5. Copy this key and save it somewhere since you won't be able to see it again

## Setting up the cron job

In your terminal of choice, open up your crobtab editor using the following command:

`crontab -e`

Enter your cron command, for example:

```
0 8 * * 2 cd ${PROJECT_DIR}/build/libs && java -DSENDGRID_API_KEY=${API_KEY} -DSENDER_EMAIL=${VERIFIED_SENDGRID_EMAIL} -DRECIPIENT_EMAIL=${EMAIL_TO} -jar fantasy-football-email-service-1.0-SNAPSHOT.jar  >/dev/null 2>&1
```

Replace `${PROJECT_DIR}` with the location of this repo.

Replace `${API_KEY}` with your Send Grid API key.

Replace email properties with your desired sender and receiver.

To make your own cronjob I recommend using [Cron Generator](https://crontab-generator.org/)