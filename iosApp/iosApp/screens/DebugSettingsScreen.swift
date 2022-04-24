//
//  DebugSettingsScreen.swift
//  iosApp
//
//  Created by Zhaolong Zhong on 4/23/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DebugSettingsScreen: View {
    
    var body: some View {
        VStack {
            DebugDesttingsNavLink(title: "Welcome", hasDivider: true) {
                WelcomeScreen()
            }
            
            DebugDesttingsNavLink(title: "Sign In", hasDivider: true) {
                SignInScreen()
            }
            
            DebugDesttingsNavLink(title: "Sign Up") {
                SignUpScreen()
            }
                
            Spacer()
        }.padding(.vertical, 8)
        .navigationBarTitle("Debug Settings", displayMode: .inline)
    }
}

struct DebugDesttingsNavLink<Content: View>: View {
    let title: String
    var hasDivider: Bool = false
    let destination: () -> Content
    
    var body: some View {
        NavigationLink(destination: destination()) {
            HStack(spacing: 8) {
                Text(title).foregroundColor(.black)
                Spacer()
                Image(systemName: "chevron.right").foregroundColor(.gray)
            }.padding(.horizontal, 6)
        }
        
        if (hasDivider) {
            Divider().overlay(Color.gray).opacity(0.3)
        }
    }
}
