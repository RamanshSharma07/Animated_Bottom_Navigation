## Animated Bottom Nav Bar for Jetpack Compose
A highly customizable, animated bottom navigation bar for Jetpack Compose, featuring a unique sliding cradle effect that follows the selected item.

## Features
Smooth Animations: Fluid slide and lift animation for the selected item.

Dynamic Cradle: The cradle in the bar smoothly moves to the active item's position.

Highly Customizable: Easily change colors, animation duration, and more.

Jetpack Compose: Built entirely in Jetpack Compose using modern Android best practices.

## 🚀 Setup
### Step 1. Add JitPack repository

Add the JitPack maven repository to your root settings.gradle.kts file.

Kotlin
dependencyResolutionManagement {
    repositories {
        //...
        maven { url 'https://jitpack.io' }
    }
}
### Step 2. Add the dependency

Add the dependency to your app's build.gradle.kts file. Remember to replace YourRepoName with your actual repository name and v1.0.0 with your latest release tag.

Kotlin
dependencies {
    implementation 'com.github.RamanshSharma07:Animated_Bottom_Navigation:v1.0.1'
}
## 💻 Usage
Using the component is simple. Create a list of NavBarItems and pass it along with your NavController to the AnimatedBottomNavBar composable, typically inside a Scaffold.

Kotlin
import com.ramanshsharma07.animatednavbar.AnimatedBottomNavBar
import com.ramanshsharma07.animatednavbar.NavBarItem

// In your main screen or MainActivity
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    // Define your navigation items
    val navItems = listOf(
        NavBarItem("home", "Home", Icons.Default.Home),
        NavBarItem("search", "Search", Icons.Default.Search),
        NavBarItem("profile", "Profile", Icons.Default.Person),
        NavBarItem("settings", "Settings", Icons.Default.Settings)
    )

    Scaffold(
        bottomBar = {
            AnimatedBottomNavBar(
                navItems = navItems,
                navController = navController
            )
        }
    ) { innerPadding ->
        // Your NavHost goes here
        AppNavigation(
            modifier = Modifier.padding(innerPadding),
            navController = navController
        )
    }
}
## 🎨 Customization (API)
You can customize the appearance and behavior of the navigation bar by passing parameters to the AnimatedBottomNavBar composable.

Parameter	Type	Default Value	Description

navItems	List<NavBarItem>	Required	The list of items to display.

navController	NavController	Required	The NavController for navigation.

modifier	Modifier	Modifier	The modifier to be applied.

barColor	Color	Color(0xFF2C2C2C)	The background color of the bar.

circleColor	Color	Color(0xFF009688)	The color of the selected item's circle.

selectedIconColor	Color	Color.White	The color of the selected icon.

unselectedIconColor	Color	Color.Gray	The color of unselected icons.

selectedTextColor	Color	Color(0xFF9C27B0)	The color of the selected item's text.

animationDurationMillis	Int	300	The duration of all animations.

## 📄 License
This project is licensed under the MIT License - see the LICENSE.md file for details.
