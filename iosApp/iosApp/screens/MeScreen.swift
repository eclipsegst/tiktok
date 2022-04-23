//
//  MeScreen.swift
//  iosApp
//
//  Created by Zhaolong Zhong on 4/23/22.
//

import SwiftUI
import shared

struct MeScreen: View {
    
    var body: some View {
        VStack {
            NavigationLink(destination: SettingsScreen()) {
                HStack {
                    Image(systemName: "gearshape").foregroundColor(.blue)
                    Text("Settings").foregroundColor(.black)
                    Spacer()
                    Image(systemName: "chevron.right").foregroundColor(.gray)
                }
            }
                
        }.navigationBarTitle("", displayMode: .inline)
    }
}
