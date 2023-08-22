<h1 align="center">Notes App</h1>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api"> <img alt="API" src="https://img.shields.io/badge/API-25%2B-brightgreen.svg?style=flat"/></a>

  <br>
  <a href="https://wa.me/+5521990399627"><img alt="WhatsApp" src="https://img.shields.io/badge/WhatsApp-25D366?style=for-the-badge&logo=whatsapp&logoColor=white"/></a>
  <a href="https://www.linkedin.com/in/vinicius-santos-b217b5168/"><img alt="Linkedin" src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white"/></a>
  <a href="mailto:viniciusantos0898@gmail.com"><img alt="Gmail" src="https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white"/></a>
</p>

<p align="center">  
  
📖 This project was created to display my tech knowledge in native Android development with Kotlin. More techincal info below.

Notes App is a native Android App made with Kotlin. The app is capable of getting information from a local, SQLite databanse, using Room; displaying the data (LiveData) in Note cards, inside a Recycler View; maintaining the model and view separation with MVVM and utilizing Clean Architecture and SOLID patterns to make the code clean, reusable and decoupled.
</p>

</br>

## Download

Download the <a href="apk/app-debug.apk?raw=true">APK </a> directly from this repo. You can check out <a href="https://www.google.com/search?q=how+to+install+apk+in+android">here</a> how to install an APK to your Android device.

## Tech Stack

- Minimum SDK Level 25
- <a href="https://kotlinlang.org/">Kotlin</a>

- [Jetpack](https://developer.android.com/jetpack?hl=pt-br)
  - Live Data: Used for observe data from a reactive point of view. Allowing the data used in UI to be updated automatically.
  - Lifecycle: Observe the Android lifecycle and manipulate the states from the UI after the lifecycle change.
  - ViewModel: Used to retrieve the data from the Model layer and update the data from View.
  - ViewBinding: Manages the XML layout views in Kotlin through a class.
  - Recycler View: Show a more efficient way of displaying a list of Views in the screen, recycling the views for better performance.
  - Room: The persistency library that offers a abstraction layer on top of SQLite to allow for a more robust database access.
    
- Architecture
  - MVVM (Model-View-ViewModel) + Clean Architecture: The first item is used to separate the logic of the app's UI, helping with testability and organization; also, the Clean Architecture helps to build the app's main system with well designed layers, who work with independency, facilitating with the scalability and maintenance.
  - Repository pattern: This pattern help with the data layer´s abstraction

- Libraries  
  - [Koin](https://insert-koin.io/): Used for Dependency Injection
  - [Junit](https://junit.org/junit5/) + [Mockito](https://site.mockito.org/): Used to create and run unit tests 
