# Connecting Amazon Echo With Facebook Using Oauth2.0

##This setup can be broken down into 3 steps:

	1) Setting up an application on facebook. This will allow you to use the facebook api.
	2) Setting up the Alexa Skill to interact with facebook.
	3) Creating a lambda function and use it to run the alexa skill that interacts with facebook.


## Setting up an application on facebook.

	1) Login to developer.facebook.com and navigate to the top right corner looking for My Apps (unless you are reading this after FB updated their UI)
	2) Click on the dropdown menu and select Add New App. Choose basic setup.
	3) Choose an appropriate display name for your app, add contact email and select a category for your facebook app. Then click on Create App Id.
	4) This provides us with an app id and an app secret, which will be used for authentication purpose later. 

## Setting up the lambda function

	1) Navigate to https://console.aws.amazon.com/lambda/home and create a lambda function. Make sure you choose the region as N. Virginia. As of now, it is required for the lambda function to be in N. Virginia region to work with Alexa Skills Kit.
	2) Click on create lambda function and skip the blue print.
	3) Give your function a name and choose runtime as Java 8
	4) Handler is the main class where the execution begins for the lambda function. For the purpose of this tutorial, provide the handler name as: 
	facebook.AlexaFacebookInformationSpeechletRequestStreamHandler i.e packageName.className
	5) Choose execution role as lambda_basic_execution and choose 512 MB of memory, which should be enough for this app.
	6) Click next and a lambda function would be created for you. Amazon will provide an Amazon Resource Number, also called ARN. Note that as it we need to provide that to the Alexa skills , so that the alexa skill knows about the lambda function which is running our code.
	7) We will be packing our code into a jar file and uploading it to this lambda function later. 


## Setting up Alexa Skills kit.

	1) Login to developer.amazon.com and choose Alexa. 
	2) We are trying to create an alexa skills kit, so click on getting started on Alexa Skills Kit.
	3) Click on add a new skill.
	4) Under the skills information tab, choose a name for your skill. This is the name for the Alexa Skill and also choose an invocation name for your skill.
	   An invocation name is what you use to to invoke your Alexa skill. For example, if you choose invocation name as 'Facebook', then you can begin your 
	   sentences by saying, "Alexa ask facebook .........."
	5) For the interaction model, we need to declare intents. Amazon echo, maps your speech to intents. For the purpose of this app, lets use one intent 
	   {
  		"intents": [
    		{
     		 "intent": "FacebookIntent"
    		}]
		}
	6) We can provide sample utterances which will train echo to recognize user input and map it to an intent. Sample utterances are written in the form of:
	   $IntentName Alexa Ask $SkillsName $Action.
	   For example, FacebookIntent Alexa ask Facebook for my information
	7) Under configuration tab, choose endpoint as AWS Lambda. Choose North America and provide the ARN number that we had saved when we created our lambda function.
	8) Choose Yes for account linking.
	9) For authorization url -> https://www.facebook.com/dialog/oauth
	10) For client id, choose the application id from the facebook application we created earlier.
	11) Add facebook.com, www.facebook.com as the domain.
	12) Add a scope, we need to provide this with the oauth call we will be making to facebook. For this tutorial, we will add scope -> public_profile
	13) Choose the Authorization Grant Type as Auth Code Grant
	14) For Auth Code Grant, we need to provide more information. Enter access token URI as:
	 https://graph.facebook.com/v2.3/oauth/access_token
	15) For Client Secret, provide the application secret from the facebook application.
	16) For Client Authentication Scheme, choose Credentials in Request Body.

## Putting it all together
	