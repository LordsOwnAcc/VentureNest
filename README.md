VentureNest App
Welcome to the VentureNest App! This Android application, built with Jetpack Compose, Firebase, and ObjectBox, supports the startup ecosystem fostered by CGC Jhanjeri College's VentureNest. The app mirrors the functionalities of the VentureNest website while managing the availability and occupancy of cabins and the Business Strategy Room (BSR).

*Table of Contents
Features

Technologies Used

Installation

Usage

Contributing

License

*Features
User Authentication: Secure login and registration via Firebase.
Cabin Management: View and book available cabins.
Business Strategy Room (BSR) Management: Check occupancy and book the BSR.
Local Data Storage: Utilize ObjectBox for efficient local data handling.
Notifications: Receive booking confirmations and reminders.
User Dashboard: A personalized area for managing bookings and user profiles.

*Technologies Used
Android: Jetpack Compose
Backend: Firebase (for authentication and real-time database)
Local Database: ObjectBox
Dependency Injection: Hilt (or your preferred method)
Networking: Retrofit (if applicable)

*Installation
To get started with the VentureNest App, follow these steps:

Clone the Repository:

bash
Copy code
git clone https://github.com/LordsOwnAcc/VentureNest-app.git
Open the Project in Android Studio.

Configure Firebase:

Set up a Firebase project in the Firebase Console.
Download the google-services.json file and place it in the app/ directory.
Sync the Project: Make sure to sync Gradle files after adding the Firebase configuration.

Run the Application: Use an emulator or physical device to run the app from Android Studio.

*Usage
Launch the app on your device or emulator.
Create an account or log in to access booking functionalities.
Navigate through the available cabins and BSR to make reservations.
Contributing
We welcome contributions to improve the VentureNest App! If you'd like to contribute, please follow these steps:

Fork the repository.
Create a new branch (git checkout -b feature/YourFeature).
Make your changes and commit them (git commit -m 'Add some feature').
Push to the branch (git push origin feature/YourFeature).
Open a Pull Request.

*License
This project is licensed under the MIT License. See the LICENSE file for more details.
