//
//  HomeScreen.swift
//  iosApp
//
//  Created by Zhaolong Zhong on 4/23/22.
//

import SwiftUI
import shared

struct HomeScreen: View {
    @EnvironmentObject var appObj: AppObservableObject

    var body: some View {
        let screenState = appObj.nav.stateProvider.getToCast(screenIdentifier: ScreenIdentifier(screen: .list, params: nil)) as! CountriesListState
        VStack {
            Text("" + "\(screenState.countriesListItems)").navigationBarTitle("Home", displayMode: .inline)
        }
    }
}
