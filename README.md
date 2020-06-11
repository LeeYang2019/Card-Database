# Tracking and selling trading cards

### Presentation Description/Overview

Buying and selling trading cards is something I enjoy doing. In particular, I enjoy buying and selling Yu-Gi- which began when I was in high school. To kill time, my friends and I would play Yu-Gi-Oh! as we waited for our parents to come pick us up from school. Now many years later, I don't play the game as much anymore but I do spend a lot of time scouring the internet and visiting local card stores to buy and sell Yu-Gi-Oh! cards. When I am selling cards in person, I put them online to sell on Ebay.

With this application, I try to solve the following problems I have with tracking and managing my collection of Yu-Gi-Oh! cards and selling them online:

1. My collection of cards are verywhere in my apartment; some are neatly put away in binders and others on shelves and my computer desk. I needed a better way to catalog them for easy tracking and managing. This application solves the above issue by letting me upload a file of cards that I own, which are them mapped into card objects and stored in a database. With a database, I can easily track and manage my card collection.

2. Outside of social media, such as youtube and facebook, and word of mouth, how do I know when a card or cards I own have gone up in value? I needed a better way to identify when I would need to sell something. This application uses a tcgplayer api for pricing. When I log into my account, the application will call the tcgplayer api and fetch the current price for each of the cards in my collection. The application will then compare the current price of a card with its previous price and if the current price has gone up X %, the application will let me display that for me. 

3. Selling online, especially on ebay is great; however, for those times when I need to sell many cards at once is there a better way to list them on ebay than having to manually key in cards one at a time?The application will use the ebay inventory api to post cards individually or as a batch to ebay.

### Screenshots of application

### Supporting Materials 

* API: http://api.tcgplayer.com/v1.32.0/pricing/product/
* API: http://api.tcgplayer.com/v1.32.0/catalog/products/
* API: http://api.tcgplayer.com/v1.32.0/catalog/categories/2/search
* Video of demo: https://youtu.be/6cBaNtQ6cP8
* https://github.com/LeeYang2019/indieProjectDemo.git

### Feedback

[Link to the peer feedback form](Feedback.md)

