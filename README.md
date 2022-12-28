# :loop: The UndoableStringBuilder

## About the project
This project main subject is to recreate and extend the capabillities of the known Java 'StringBuilder' by writing our own 'UndoableStringBuilder' that will be capable of rolling back changes by adding the method undo() with consideration of best complexity possiable to make that happen.

### Authors
* [Tal Malka](https://github.com/TalMaIka)    
* [Yann Chich](https://github.com/yannchich)

 ## Project Description
 
 ![Screenshot](https://serving.photos.photobox.com/42202007e51b950bbd8057d26148ad5295045327922b125db63ac9fa66ab4bcd4509ad20.jpg) 
 The image above breaks down the project by classes interfaces and methods.
 
 As shown 'GroupAdmin' implements 'Sender' and 'ConcreteMember' implements 'Member' Interface.
 'GroupAdmin' resembles the 'Observer' who sends the updates and 'ConcreteMember' resembles the 'Observerable' who gets updates.
 We will explain that project as an Youtube channel: 
 * 'UndoableStringBuilder' -> as Video   
 * 'GroupAdmin' -> as Channel Owner      
 * 'ConcreteMember' -> As User who subscribed to the channel.
 
 * First we create a "Channel" named ABC.      
   ```GroupAdmin ABC = new GroupAdmin();```
   
 *  We'll create a "Member" named FirstUser.     
 ```ConcreteMember FirstUser = new ConcreteMember("User");```  
   
   
 *  Than We'll make the registration proccess to the channel.    
 ```ABC.register(FirstUser);```

 * The procces will make sure that every change/update made by the channel owner will get to the registered members.
   For example we will append a string by ABC.append("First Video") (resembles to new video beeing uploaded to the channel)   
 ```ABC.append("First Video");```
   
 * Then the method execured after every change made notifyMembers()   
    ```for (Member m:MemberList) {m.update(USB);}```    
    
 That will make sure that the members registered to the channel will be notified in every update made.




## Version History

* 0.2 - Utillazaion of 'UndoableStringBuilder' based on 'Observer OOP'
    * :speech_balloon: Notifing members on changes made for the 'USB' they ase registered for.
    
* 0.1 - Recreation of the StringBuilder -> UndoableStringBuilder including:
    * append(), delete(), insert(), replace(), reverse(), toString()
    * undo() -> Main subject of 0.1


