//
//  SettingsScreen.swift
//  iosApp
//
//  Created by Zhaolong Zhong on 4/23/22.
//

import SwiftUI
import shared

struct SettingsScreen: View {
    
    @State private var isShowingDetailView = false

    var body: some View {
        VStack {
            
            SettingEntryHeader(title: "Account")
            SettingEntryNavLink(title: "Manage Account", icon: "person") {
                isShowingDetailView = true
            }
            SettingEntryNavLink(title: "Share profile", icon: "arrowshape.turn.up.forward")
            SettingEntryHeader(title: "About")
            SettingEntryNavLink(title: "Terms of Use", icon: "doc")
            SettingEntryNavLink(title: "Privacy Policy", icon: "lock")
            SettingEntryHeader(title: "Login")
            SettingEntryNavLink(title: "Log out", icon: "rectangle.portrait.and.arrow.right")
            Spacer()
        }.navigationBarTitle("Settings", displayMode: .inline)
    }
}

struct SettingEntryHeader: View {
    var title = ""
    var body: some View {
        HStack {
            Text(title)
                .font(.system(size: 12))
                .fontWeight(.semibold)
                .foregroundColor(.gray.opacity(0.8))
                .textCase(.uppercase)
            Spacer()
        }.padding(.all, 15)
    }
}

struct SettingEntryNavLink: View {
    let title: String
    var icon: String = "gearshape"
    var hasDivider: Bool = false
    var action: () -> Void = {}
    
    var body: some View {
        Button(action: action) {
            Image(systemName: icon)
                .frame(width: 30, alignment: .center)
                .foregroundColor(.gray)
            HStack(spacing: 8) {
                Text(title)
                    .font(.system(size: 14))
                    .fontWeight(.regular)
                    .foregroundColor(.black.opacity(0.8))
                Spacer()
                Image(systemName: "chevron.right").foregroundColor(.gray)
            }.padding(.horizontal, 6)
        }
        .padding(.leading, 15)
        .padding(.trailing, 15)
        .padding(.top, 10)
        .padding(.bottom, 10)
        
        if (hasDivider) {
            Divider().overlay(Color.gray).opacity(0.3)
        }
    }
}
