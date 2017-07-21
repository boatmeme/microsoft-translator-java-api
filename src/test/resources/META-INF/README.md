# config.properties

This file contains any properties required for Unit Testing, chiefly, your Microsoft Translator Subscription Key. 

    microsoft.translator.subscription.key=INSERT_SUBSCRIPTION_KEY

This Subscription Key is _only_ used for Unit Testing. For normal usage, the Subscription Key is set in code at runtime.

If you've forked this branch and wish to run the Unit Tests, please replace the placeholder value with _your_ Subscription Key. You should then be careful not to check this file back into your branch. Ignore the changes locally by issuing the following command

    git update-index --assume-unchanged <relative_path_to>/config.properties