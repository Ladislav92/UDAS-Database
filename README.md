# UDAS-Database
JavaFX app that connects to and manages database for Organization UDAS


Chat with us on [discord server](https://discord.gg/yRpVrQC)


# Currently application is in transition to network one.

JavaServlets running in Tomcatserver are used as as backend that communicates with JavaFX app trough JSON (wannabe rpc).

Right now, we are trying to provide first working version of the application to UDAS organisation as soon as possible.

# For first working version of the app:

1. We do not want to change existing code that works fine even if it is bad (and it is). What works - let it do its job for now.
2. We want as less dependencies as possible - we want to use only what we really need 
(this includes design pattern / abstract class / interface abuse).
3. We want to switch current application into "network" one just because they have like 6 computers within organization
 and they all need to have access to the database which will be running on one of the computers (maybe raspbery Pi at some point).
4. For first version we need basic, CRUD application with parametrized search.

Also, for first, nasty version, "backend" part is mostly done and most work needed is actually on client (JavaFX) side.

### Reasons we are doing first version on cheap and nasty way is simple: 

1. We want to give them working application ASAP. 
2. They will be able to start entering the member data (searching, updating, CRUD stuff).
3. When they actually start to use the app - they will see what could be changed/added so there will be some feedback.
4. We don't want to overcomplicate/overengeneer stuff because simpler the better.

After we provide them first working version of the application structure and internal mechanisms can be discussed and refactoring 
for thigns which are good candidates for that and for which refactoring makes sense will be done and we can continue to implement 
stuff for second (and later on) third version of the application.

For first version, 

## BACKEND right now basically needs 2 classes:

Servlet that dispatches calls to DAO. 
And class that converts JSON to Member objects and list of Strings probably.

## FRONTEND needs (beside all the missing GUI stuff and things):
JSON to Member converter as backend (will be reconsidered in version 2, right)
Class that does request to backend instead of DAO it used.

#### IMPORTANT NOTE:
We don't mind about security of the app (right now) nor its speed (we porbably won't ever) - it will run on local network.


NOTE:
About Login handling ... for first version it will be blasphemic client side login where username and password will be hardcoded.
For efficiency, you know. ;)


## Also check [readme from develop branch](https://github.com/Ladislav92/UDAS-Database/blob/develop/README.md) for more detailed context about application and the idea itself.
