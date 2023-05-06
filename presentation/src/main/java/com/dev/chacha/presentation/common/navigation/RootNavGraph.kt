package com.dev.chacha.presentation.common.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.*
import com.dev.chacha.presentation.activity.MainScreen


/*
@RequiresApi(Build.VERSION_CODES.P)
@ExperimentalAnimationApi
@Composable
fun ExperimentalAnimationNav() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(navController, startDestination = Graph.AUTHENTICATION) {
        composable(
            Graph.AUTHENTICATION,
            enterTransition = {
                when (initialState.destination.route) {
                    Graph.AUTHENTICATION ->
                        slideInHorizontally(initialOffsetX = { it })
                    else -> null
                }
                slideInHorizontally(initialOffsetX = { -it })
            },
            exitTransition = {
                when (initialState.destination.route) {
                    Graph.AUTHENTICATION -> slideOutHorizontally(
                        targetOffsetX = { -it },
                        animationSpec = tween(durationMillis = 1000)
                    )
                    else -> null
                }

            },
            popEnterTransition = {
                when (initialState.destination.route) {
                    Graph.AUTHENTICATION -> slideInHorizontally(
                        initialOffsetX = { -it },
                        animationSpec = tween(durationMillis = 1000)
                    )
                    else -> null
                }

            },
            popExitTransition = {
                when (initialState.destination.route) {
                    Graph.AUTHENTICATION -> slideOutHorizontally(
                        targetOffsetX = { it },
                        animationSpec = tween(durationMillis = 1000)
                    )
                    else -> null
                }

            }
        ) { authNavGraph(navController) }
        composable(
            "Red",
            enterTransition = {
                when (initialState.destination.route) {
                    "Blue" ->
                        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(700))
                    "Green" ->
                        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up, animationSpec = tween(700))
                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    "Blue" ->
                        slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(700))
                    "Green" ->
                        slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Up, animationSpec = tween(700))
                    else -> null
                }
            },
            popEnterTransition = {
                when (initialState.destination.route) {
                    "Blue" ->
                        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(700))
                    "Green" ->
                        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Down, animationSpec = tween(700))
                    else -> null
                }
            },
            popExitTransition = {
                when (targetState.destination.route) {
                    "Blue" ->
                        slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(700))
                    "Green" ->
                        slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Down, animationSpec = tween(700))
                    else -> null
                }
            }
        ) { MainScreen() }
        navigation(
            startDestination = "Green",
            route = "Inner",
            enterTransition = { expandIn(animationSpec = tween(700)) },
            exitTransition = { shrinkOut(animationSpec = tween(700)) }
        ) {
            composable(
                "Green",
                enterTransition = {
                    when (initialState.destination.route) {
                        "Red" ->
                            slideIntoContainer(
                                AnimatedContentTransitionScope.SlideDirection.Up, animationSpec = tween(700)
                            )
                        else -> null
                    }
                },
                exitTransition = {
                    when (targetState.destination.route) {
                        "Red" ->
                            slideOutOfContainer(
                                AnimatedContentTransitionScope.SlideDirection.Up, animationSpec = tween(700)
                            )
                        else -> null
                    }
                },
                popEnterTransition = {
                    when (initialState.destination.route) {
                        "Red" ->
                            slideIntoContainer(
                                AnimatedContentTransitionScope.SlideDirection.Down, animationSpec = tween(700)
                            )
                        else -> null
                    }
                },
                popExitTransition = {
                    when (targetState.destination.route) {
                        "Red" ->
                            slideOutOfContainer(
                                AnimatedContentTransitionScope.SlideDirection.Down, animationSpec = tween(700)
                            )
                        else -> null
                    }
                }
            ) { GreenScreen(navController) }
        }
    }
}
*/

/*@Composable
fun RootNavGraph(
    navController: NavHostController
) {
    AnimatedNavHost(navController, startDestination = Graph.AUTHENTICATION) {
        composable(
            Graph.AUTHENTICATION,
            enterTransition = {
                when (initialState.destination.route) {
                    Graph.AUTHENTICATION ->
                        slideInHorizontally(initialOffsetX = { it })
                    else -> null
                }
                slideInHorizontally(initialOffsetX = { -it })
            },
            exitTransition = {
                when (initialState.destination.route) {
                    Graph.AUTHENTICATION -> slideOutHorizontally(
                        targetOffsetX = { -it },
                        animationSpec = tween(durationMillis = 1000)
                    )
                    else -> null
                }

            },
            popEnterTransition = {
                when (initialState.destination.route) {
                    Graph.AUTHENTICATION -> slideInHorizontally(
                        initialOffsetX = { -it },
                        animationSpec = tween(durationMillis = 1000)
                    )
                    else -> null
                }

            },
            popExitTransition = {
                when (initialState.destination.route) {
                    Graph.AUTHENTICATION -> slideOutHorizontally(
                        targetOffsetX = { it },
                        animationSpec = tween(durationMillis = 1000)
                    )
                    else -> null
                }

            }
        ) { authNavGraph(navController) }
        composable(
            Graph.HOME,
            enterTransition = {
                when (initialState.destination.route) {
                    Graph.AUTHENTICATION -> slideInHorizontally(
                        initialOffsetX = { it },
                        animationSpec = tween(durationMillis = 1000)
                    )
                    else -> null
                }
            },
            exitTransition = {
                when (initialState.destination.route) {
                    Graph.AUTHENTICATION -> slideOutHorizontally(
                        targetOffsetX = { -it },
                        animationSpec = tween(durationMillis = 1000)
                    )
                    else -> null
                }
            },
            popEnterTransition = {
                when (initialState.destination.route) {
                    Graph.AUTHENTICATION -> slideInHorizontally(
                        initialOffsetX = { -it },
                        animationSpec = tween(durationMillis = 1000)
                    )
                    else -> null
                }
            },
            popExitTransition = {
                when (initialState.destination.route) {
                    Graph.AUTHENTICATION -> slideOutHorizontally(
                        targetOffsetX = { it },
                        animationSpec = tween(durationMillis = 1000)
                    )
                    else -> null
                }
            }
        ) { MainScreen() }
    }
}*/



/*
@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RootNavGraph(
    navController: NavHostController
) {
    AnimatedNavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = AuthScreen.Onboard.route,
        enterTransition = {

            when (initialState.destination.route) {
                Graph.AUTHENTICATION ->
                    slideInHorizontally(initialOffsetX = { it })
                else -> null
            }
            slideInHorizontally(initialOffsetX = { -it })
        },
        exitTransition = {
            when (initialState.destination.route) {
                Graph.AUTHENTICATION -> slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(durationMillis = 1000)
                )
                else -> null
            }
            slideOutHorizontally(
                targetOffsetX = { -it },
                animationSpec = tween(durationMillis = 1000)
            )
        },
        popEnterTransition = {
            when (initialState.destination.route) {
                Graph.AUTHENTICATION -> slideInHorizontally(
                    initialOffsetX = { -it },
                    animationSpec = tween(durationMillis = 1000)
                )
                else -> null
            }
            slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(durationMillis = 1000),

                )
        },
        popExitTransition = {
            when (initialState.destination.route) {
                Graph.AUTHENTICATION -> slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(durationMillis = 1000)
                )
                else -> null
            }
            slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(durationMillis = 1000)
            )
        }

    ) {
        composable(route = Graph.AUTHENTICATION) {
            authNavGraph(navController = navController)
        }
        composable(route = Graph.HOME) {
            MainScreen()
        }
    }

}*/


@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun RootNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ){
        authNavGraph(navController = navController)

        composable(route = Graph.HOME){
            MainScreen()
        }
    }

}


object Graph {
    const val HOME = "home_graph"
    const val AUTHENTICATION = "auth_graph"
    const val ROOT = "root_graph"
    const val BIOMETRIC = "biometric"
    const val DETAILS = "detail_route"
    const val SEND_MONEY = "send_money"
}