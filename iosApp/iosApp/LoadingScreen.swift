//
//  LoadingScreen.swift
//  iosApp
//
//  Created by Zhaolong Zhong on 4/18/22.
//

import SwiftUI

struct LoadingScreen: View {
    var body: some View {
        VStack {
            Spacer()
            Text("Loading...")
            Spacer().frame(height: 30)
            ProgressView().progressViewStyle(CircularProgressViewStyle())
            Spacer()
        }
    }
}
