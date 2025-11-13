# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

DW_Insta is an Instagram-like Android application built with Java. It features user authentication, image sharing, chat functionality, and user profiles, all powered by Firebase backend services.

**Package**: `com.kaywalker.newone`

## Build & Development Commands

### Building the Project
```bash
# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Clean build
./gradlew clean
```

### Running Tests
```bash
# Run unit tests
./gradlew test

# Run instrumented tests (requires connected device/emulator)
./gradlew connectedAndroidTest

# Run specific test class
./gradlew test --tests com.kaywalker.newone.ExampleInstrumentedTest
```

### Installing and Running
```bash
# Install debug APK on connected device
./gradlew installDebug

# Uninstall app
adb uninstall com.kaywalker.newone
```

### Gradle Tasks
```bash
# List all available tasks
./gradlew tasks

# Check dependencies
./gradlew dependencies
```

## Architecture

### Application Flow

1. **IntroActivity** (Launcher) → Entry point with splash/intro screen
2. **MainActivity** → Login screen with email/password and Google Sign-In
3. **RegisterActivity** → New user registration
4. **HomeActivity** → Google Sign-In landing page displaying user profile
5. **CenterActivity** → Main application hub with bottom navigation

### Fragment-Based Navigation

CenterActivity hosts a BottomNavigationView with four main fragments:

- **Frag_Home** (Home feed) - Main content feed
- **Frag_Board** (Board) - Image board/gallery view
- **Frag_Profile** (Profile) - User profile management
- **Frag_List** (User List) - Browse other users

Fragment switching is handled in `CenterActivity.setFrag(int n)` using FragmentManager and FragmentTransaction.

### Firebase Integration

The app uses three Firebase services:

- **Firebase Authentication**: Email/password and Google Sign-In
  - Initialized in MainActivity and used across authentication activities
  - Google Sign-In configured with `default_web_client_id` from google-services.json

- **Firebase Realtime Database**: Data storage at `FirebaseDatabase.getInstance()`
  - Primary reference: `"Users"` node for user data
  - Additional nodes for images, chat messages

- **Firebase Storage**: Image upload and retrieval
  - Handles user profile images and shared content

### Data Models

Located in `com.kaywalker.newone.model`:

- **UserAccount**: User authentication data (idToken, email, password)
- **Image**: Image metadata (ImageUrl, Key, StorageKey)
- **ChatDTO**: Chat message data structure
- **dataholder**: Temporary data holder for passing data between components

### Key Activities

- **AddActivity**: Upload new images/content
- **ChatActivity**: Direct messaging functionality
- **UserActivity**: View other user profiles
- **PwActivity**: Password recovery
- **FullScreenActivity**: Display images in full screen
- **NewActivity**: Create new posts

### Adapters & RecyclerViews

Located in `com.kaywalker.newone.adapter`:

- **ImageAdapter**: Displays image grids with upload functionality
  - Handles image picking via `PIKE_IMAGE_CODE` result
  - Static members: `LoadChangedImage`, `filepath`
- **ShareAdapter**: Social sharing features
- **FragmentAdapter**: ViewPager adapter for fragments
- **ImageHolder**: ViewHolder pattern for image items

### Image Loading Libraries

The app uses two image loading libraries:

- **Glide** (v4.13.0): Primary image loading, especially for Google profile images
- **Picasso** (v2.8): Used in CenterActivity for image result handling

**Convention**: Use Glide for new features as it's more actively maintained.

### Permissions

Required permissions in AndroidManifest.xml:
- `INTERNET`: Firebase and network operations
- `READ_PHONE_STATE`: Device identification
- `WRITE_EXTERNAL_STORAGE` / `READ_EXTERNAL_STORAGE`: Image handling

### Build Configuration

- **compileSdk**: 32
- **minSdk**: 23
- **targetSdk**: 32
- **Java Version**: 1.8
- **AGP Version**: 7.2.1

## Important Notes

### google-services.json
The Firebase configuration file (`app/google-services.json`) contains project-specific settings. When working with Firebase features, ensure this file is properly configured for your Firebase project.

### FileProvider
The app uses FileProvider with authority `com.kaywalker.newone` for sharing files. Configuration is in `res/xml/paths`.

### Activity Launch Flow
- IntroActivity is the LAUNCHER activity (entry point)
- After login, users navigate to CenterActivity which becomes the main hub
- Google Sign-In users go through HomeActivity before reaching CenterActivity

### Static References in ImageAdapter
ImageAdapter uses static members (`LoadChangedImage`, `filepath`) to handle image selection results. Be aware of potential memory leaks when modifying this pattern.
