//
//  WelcomeScreen.swift
//  iosApp
//
//  Created by Zhaolong Zhong on 4/23/22.
//

import SwiftUI
import shared

struct WelcomeScreen: View {
    
    var body: some View {
        VStack {
            Text("Welcome Screen").foregroundColor(.orange)
            
            NavigationLink(destination: SignInScreen()) {
                Text("Sign In").foregroundColor(.black)
            }
            
            NavigationLink(destination: SignUpScreen()) {
                Text("Sign Up").foregroundColor(.black)
            }
        }
    }
}
