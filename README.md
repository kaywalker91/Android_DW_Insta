# DW_Insta - Instagram Clone Android Application

![dw_github1](https://user-images.githubusercontent.com/104833740/191170301-812e23f5-7306-40e0-b9db-1e41350b3d8c.png)

## Overview

**DW_Insta** is a fully-functional Instagram-inspired Android application built with Java and Firebase. This project demonstrates modern Android development practices including user authentication, real-time messaging, image sharing, and social networking features.

**Package**: `com.kaywalker.newone`

## Demo

<div align="center">

https://user-images.githubusercontent.com/104833740/192204455-34d95e83-5cad-43a9-84d6-6b6144dd1e17.mp4

</div>

## Key Features

### User Management
- Email/password authentication
- Google Sign-In integration
- User profile management with avatar upload
- Profile viewing and editing

### Social Features
- Photo feed with real-time updates
- Image upload with caption support
- User discovery and search
- Follow/unfollow functionality

### Messaging
- Direct messaging between users
- Real-time chat synchronization
- Message history persistence

### Media Management
- Gallery integration for photo selection
- Full-screen image viewing
- Image optimization and caching
- Profile picture upload and management

## Technical Stack

### Core Technologies
- **Language**: Java 1.8
- **SDK**: Compile/Target SDK 32, Minimum SDK 23 (Android 6.0)
- **Build System**: Gradle 7.x

### Firebase Services
- **Authentication**: Email/password and Google Sign-In
- **Realtime Database**: User data and messaging storage
- **Cloud Storage**: Profile images and content storage

### Key Libraries
| Library | Version | Purpose |
|---------|---------|---------|
| Glide | 4.13.0 | Primary image loading and caching |
| Picasso | 2.8 | Image loading support |
| Firebase Auth | latest | User authentication |
| Firebase Database | latest | Real-time data synchronization |
| Firebase Storage | latest | Cloud file storage |
| Material Components | latest | Modern UI components |

## Architecture

### Application Flow
```
IntroActivity (Splash)
    ↓
MainActivity (Login/Registration)
    ↓
CenterActivity (Main Hub)
    ├── Frag_Home (Feed)
    ├── Frag_Board (Gallery)
    ├── Frag_Profile (User Profile)
    └── Frag_List (User Discovery)
```

### Core Components

**Activities**:
- `IntroActivity`: Splash screen with app initialization
- `MainActivity`: Authentication gateway (login/signup)
- `CenterActivity`: Main navigation hub with bottom navigation
- `AddActivity`: Content upload interface
- `ChatActivity`: Direct messaging screen
- `UserActivity`: Profile viewing screen
- `FullScreenActivity`: Full-screen image viewer

**Fragments**:
- `Frag_Home`: Main feed displaying user posts
- `Frag_Board`: Gallery view of all images
- `Frag_Profile`: User profile management
- `Frag_List`: User discovery and search

**Data Models**:
- `UserAccount`: User profile information
- `Image`: Post content and metadata
- `ChatDTO`: Chat message data
- `dataholder`: Temporary data transfer

### Firebase Database Structure
```
firebase-root/
├── Users/
│   └── [userId]/
│       ├── name
│       ├── email
│       ├── profileImageUrl
│       └── ...
├── Posts/
│   └── [postId]/
│       ├── imageUrl
│       ├── caption
│       ├── userId
│       └── timestamp
└── Chats/
    └── [chatId]/
        ├── messages
        └── participants
```

## Getting Started

### Prerequisites
- Android Studio Arctic Fox or newer
- JDK 1.8 or higher
- Android SDK 32
- Firebase account and project setup
- Google Sign-In credentials (OAuth 2.0)

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/kaywalker91/Android_DW_Insta.git
cd Android_DW_Insta
```

2. **Firebase Configuration**
- Create a new Firebase project at [Firebase Console](https://console.firebase.google.com/)
- Enable Authentication (Email/Password and Google Sign-In)
- Enable Realtime Database
- Enable Cloud Storage
- Download `google-services.json` and place it in `app/` directory

3. **Configure Google Sign-In**
- Enable Google Sign-In in Firebase Authentication
- Add SHA-1 and SHA-256 fingerprints to Firebase project
- Download updated `google-services.json`

4. **Build the project**
```bash
# Install dependencies
./gradlew build

# Run debug build
./gradlew installDebug
```

### Development Commands

```bash
# Build APK
./gradlew assembleDebug          # Debug APK
./gradlew assembleRelease        # Release APK

# Testing
./gradlew test                   # Unit tests
./gradlew connectedAndroidTest   # Instrumented tests

# Installation
./gradlew installDebug           # Install debug build on device

# Clean build
./gradlew clean
```

## Configuration

### Required Permissions
The app requires the following permissions (declared in `AndroidManifest.xml`):
- `INTERNET`: Network communication
- `READ_PHONE_STATE`: Device information
- `READ_EXTERNAL_STORAGE`: Gallery access
- `WRITE_EXTERNAL_STORAGE`: File storage (SDK < 29)

### FileProvider Configuration
**Authority**: `com.kaywalker.newone`

Configured for secure file sharing between app and external apps (camera, gallery).

## Usage

### First-Time Setup
1. Launch the app
2. Create an account or sign in with Google
3. Set up your profile with name and profile picture
4. Grant necessary permissions when prompted

### Main Features Usage

**Upload a Photo**:
1. Tap the "+" button in the bottom navigation
2. Select an image from gallery
3. Add a caption (optional)
4. Tap "Upload"

**Direct Messaging**:
1. Navigate to a user's profile
2. Tap the message icon
3. Send messages in real-time

**View Feed**:
- Home tab shows posts from all users
- Scroll to load more content
- Tap images for full-screen view

**Manage Profile**:
- Profile tab shows your posts and information
- Edit profile to update name and avatar
- View your post history

## Project Structure

```
app/src/main/
├── java/com/kaywalker/newone/
│   ├── activities/
│   │   ├── IntroActivity.java
│   │   ├── MainActivity.java
│   │   ├── CenterActivity.java
│   │   ├── AddActivity.java
│   │   ├── ChatActivity.java
│   │   ├── UserActivity.java
│   │   └── FullScreenActivity.java
│   ├── fragments/
│   │   ├── Frag_Home.java
│   │   ├── Frag_Board.java
│   │   ├── Frag_Profile.java
│   │   └── Frag_List.java
│   ├── models/
│   │   ├── UserAccount.java
│   │   ├── Image.java
│   │   ├── ChatDTO.java
│   │   └── dataholder.java
│   └── adapters/
│       └── [RecyclerView adapters]
├── res/
│   ├── layout/
│   ├── drawable/
│   └── values/
└── AndroidManifest.xml
```

## Firebase Security Rules

### Database Rules (Recommended)
```json
{
  "rules": {
    "Users": {
      "$uid": {
        ".read": "auth != null",
        ".write": "$uid === auth.uid"
      }
    },
    "Posts": {
      ".read": "auth != null",
      ".write": "auth != null"
    }
  }
}
```

### Storage Rules (Recommended)
```
service firebase.storage {
  match /b/{bucket}/o {
    match /profile_images/{userId}/{allPaths=**} {
      allow read: if request.auth != null;
      allow write: if request.auth != null && request.auth.uid == userId;
    }
    match /post_images/{allPaths=**} {
      allow read: if request.auth != null;
      allow write: if request.auth != null;
    }
  }
}
```

## Known Issues & Limitations

- Minimum SDK 23 (Android 6.0+) required
- Google Sign-In requires proper SHA fingerprint configuration
- Storage permissions needed for Android 10 and below
- Image upload size may be limited by Firebase Storage quotas

## Troubleshooting

### Build Failures
```bash
# Clean build artifacts
./gradlew clean
./gradlew build --refresh-dependencies
```

### Firebase Connection Issues
- Verify `google-services.json` is in `app/` directory
- Check Firebase project configuration
- Ensure Firebase dependencies are up to date
- Verify internet connectivity

### Google Sign-In Issues
- Confirm SHA-1 and SHA-256 fingerprints are added to Firebase
- Download updated `google-services.json` after adding fingerprints
- Check OAuth 2.0 client ID configuration

### Permission Errors
- Grant storage permissions in device settings
- For Android 11+, check scoped storage compatibility

## Contributing

Contributions are welcome! To contribute:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Development Guidelines
- Follow Java coding conventions
- Add comments for complex logic
- Test on multiple Android versions
- Update documentation for new features

## Testing

### Unit Tests
```bash
./gradlew test
```

### Instrumented Tests
```bash
./gradlew connectedAndroidTest
```

Requires a connected device or emulator.

## License

This project is open-source and available for educational purposes.

## Authors

- **kaywalker91** - Initial development
- **claude** - Documentation and enhancements

## Acknowledgments

- Firebase for backend infrastructure
- Glide and Picasso for image loading
- Material Design components
- Android Developer community

## Contact

For questions or support, please open an issue on GitHub.

---

**Note**: This is an educational project demonstrating Android development practices. For production use, implement additional security measures, error handling, and optimization.
