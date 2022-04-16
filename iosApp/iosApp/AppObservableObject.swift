//
//  AppObservableObject.swift
//  iosApp
//
//  Created by Zhaolong Zhong on 4/15/22.
//

import SwiftUI
import shared

class AppObservableObject: ObservableObject {
    let model : TViewModel = TViewModel.Factory().getIOSInstance()
    var nav : Navigation {
        return self.model.navigation
    }
    
    @Published var appState : AppState
    
    init() {
        self.appState = model.getDefaultAppState()
        model.onChange { newState in
            self.appState = newState
        }
    }
}
