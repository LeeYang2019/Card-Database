Application Flow

User Sign Up
1. User chooses to sign up or navigates away (signing up is a requirement for using the app).
2. User fills out the sign-up form and submits.
3. Request goes to the sign up servlet.
4. Servlet creates a user object and then creates user in the database.
5. A response is displayed to the user confirming sign-up.

User Sign In
1. User enters username and password on form and submits 

Initial Application Setup
1. When user first signs in, there will not be any database information. A prompt will ask if user wants to upload cards into the card database.
2. The user chooses to upload a file containing his or her cards (This is an Excel spreadsheet the user fills out).
3. The file is read in via the file reader servlet.
4. The Servlet will parse the string into tokens and create card objects, and then inserts these objects into the card database.
5. The user can also choose not to upload a file.

Adding a card
1. User clicks on the add tab on the menu.
2. User fills out information on the add page and clicks submit. 
3. Request goes to the card add servlet.
4. Servlet creates a card object and then inserts these objects into the card database.

Search a card
1. User clicks on the search tab on the menu
2. User fills out information on the quick search bar and clicks submit.
3. User can also fill out information on the advanced search section and click submit
4. Request goes to the card search servlet
5. Servlet queries the database and returns search results to the search results page

Edit a card
1. User selects a result on the search results page.
2. User is navigated to the card page where user then clicks to edit the card.
3. User updates information on the edit pate and clicks submit.
4. Request goes to the card edit servlet.
5. Servlet updates the card object

Sell a card
1. User selects a result on the search results page.
2. User is navigated to the card page where user then clicks to sell the card.
3. Card is listed on the selling page.
4. User navigates to the selling page and clicks post to Ebay.
5. User can also select a result from the home page navigating user to the card page.
6. User is prompted to provide login credentials for Ebay.
7. User enters login and password and clicks submit.
8. Request goes to the eBay servlet.
9. Servlet forwards request to Ebay API.
