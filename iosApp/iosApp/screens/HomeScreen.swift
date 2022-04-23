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
        let countriesListState = appObj.nav.stateProvider.getToCast(screenIdentifier: ScreenIdentifier(screen: .list, params: nil)) as! CountriesListState
        VStack {
            if countriesListState.isLoading {
                LoadingScreen()
            } else {
                List {
                    if countriesListState.countriesListItems.count == 0 {
                        HStack(spacing: 0) {
                            Text("empty list")
                        }
                    } else {
                        Section(header: CountriesListHeader()) {
                            ForEach (countriesListState.countriesListItems, id: \.name) { item in
                                NavigationLink(destination: CountryDetailScreen(name: item.name, nav: appObj.nav)) {
                                    ListRow(
                                        item: item,
                                        favorite: false,
                                        onFavoriteIconClick: {}
                                    )
                                }
                            }
                        }
                    }
                }
                .listStyle(InsetGroupedListStyle())
            }
        }.navigationBarTitle("Home", displayMode: .inline)
    }
}

let customBgColor = Color(.sRGB, red: 55/255, green: 0, blue: 179/255, opacity: 1) // material Purple700
let linkColor = Color(.sRGB, red: 209/255, green: 190/255, blue: 245/255, opacity: 1) // light pink
let magentaColor = Color(.sRGB, red: 1, green: 0, blue: 1, opacity: 1) // purple
let greyColor = Color(.sRGB, red: 170/255, green: 170/255, blue: 170/255, opacity: 1) // grey
let lightGreyColor = Color(.sRGB, red: 200/255, green: 200/255, blue: 200/255, opacity: 1) // light grey

struct CountriesListHeader: View {
    
    var body: some View {
        HStack {
            Text("country").font(Font.caption).frame(alignment: .leading)
            Spacer()
            Text("first\ndose").font(Font.caption).multilineTextAlignment(.center)
                .frame(width: 60)
            Text("fully\nvax'd").font(Font.caption).multilineTextAlignment(.center)
                .frame(width: 60)
            Text("favorite?").font(Font.caption).frame(alignment: .center)
                .frame(width: 80)
                .padding(.trailing, 25)
        }.frame(height: 50)
    }
}

struct ListRow: View {
    var item : CountriesListItem
    var favorite : Bool
    var onFavoriteIconClick: () -> Void
    
    var body: some View {
        HStack {
            Text(item.name).font(Font.subheadline).bold().frame(alignment: .leading)
            Spacer()
            Text("\(item.firstDosesPerc)").font(Font.subheadline).multilineTextAlignment(.trailing)
                .frame(width: 60)
            Text("\(item.fullyVaccinatedPerc)").font(Font.subheadline).multilineTextAlignment(.trailing)
                .frame(width: 60)
            Image(systemName: favorite ? "star.fill" : "star").foregroundColor(favorite ? magentaColor : .gray)
                .highPriorityGesture(TapGesture().onEnded(onFavoriteIconClick))
                .frame(width: 70)
        }
    }
}
