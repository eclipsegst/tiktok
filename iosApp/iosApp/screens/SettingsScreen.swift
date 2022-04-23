//
//  SettingsScreen.swift
//  iosApp
//
//  Created by Zhaolong Zhong on 4/23/22.
//

import SwiftUI
import shared

struct SettingsScreen: View {

    var body: some View {
        VStack {
            HStack {
                Button(action: {
                }) {
                    Text("Logout").foregroundColor(.black).padding(12).background(Color.orange).cornerRadius(3)
                }
            }
            .navigationBarTitle("Settings", displayMode: .inline)
        }
    }
}
