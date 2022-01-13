# finalproject

User stories

As the owner of this store, I want to have a fine website so that many people are interested in my products. 
As the owner of this store, I want to show customers about specific product details so that customers can see the details and add them to their cart. 
As the administrator of this store, I want customers to see their cart items when they log in so that they can check out the items easily. 
As the administrator of this store, I want customers to see their previous order lists when they check out their items so that customers can see their order lists.
As the administrator of this store, I want customers who can’t access some specific pages so that secure information.
As the administrator of this store, I want to see my product, users, and order lists easily so that I can manage those lists.
As the administrator of this store, I want to see the products list on the website so that when I register a new item, the item will show up in the list automatically. 

Technical challenges

Implementing the cart function, and connecting the cart function to the order function, was the most challenging thing for me. 
When users want to put items in their cart, I get the order list by user Id and check the order list whether it has a pending order or not. 
If there is a pending order, I get the order and get the cart in the order, and put the items in the cart. 
If there is no pending order in the order list, I make a new order and set the status as pending. 
In the new pending order, I make a new cart and put items in the cart and save the cart in the order. That is what I did for implementing the cart and order function. 

I wanted to implement that different role has different authorities. 
To do that I used spring security and made two different roles. One is a customer, and another one is an admin. 
Admin user has more authority than customer user. Only admin users can edit the item, product, user list and see the admin section. 
