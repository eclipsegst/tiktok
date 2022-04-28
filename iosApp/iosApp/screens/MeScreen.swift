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
            Image("plant")
                .resizable()
                .frame(width: 60.0, height: 60.0)
                .clipShape(Circle())
                .shadow(radius: 10)
                .overlay(Circle().stroke(Color.orange, lineWidth: 1))
            
            Text("@username")
                .font(.system(size: 14).bold())
                .foregroundColor(.black.opacity(0.8))
                .padding()
            
            HStack {
                Spacer()
                FollowBlock(count: 1, title: "Following")
                FollowBlock(count: 2, title: "Followers")
                FollowBlock(count: 3, title: "Likes")
                Spacer()
            }
            
            HStack {
                Button(action: {
                    print("Edit profile tapped!")
                }) {
                    Text("Edit profile")
                        .fontWeight(.semibold)
                        .font(.system(size: 14).bold())
                        .foregroundColor(.black.opacity(0.8))
                        .padding(.horizontal, 30)
                        .padding(.vertical, 10)
                        .background(RoundedRectangle(cornerRadius: 2).stroke(.gray.opacity(0.4), lineWidth: 0.5))
                }
            }
            
            NavigationLink(destination: SettingsScreen()) {
                HStack {
                    Image(systemName: "gearshape").foregroundColor(.blue)
                    Text("Settings").foregroundColor(.black)
                    Spacer()
                    Image(systemName: "chevron.right").foregroundColor(.gray)
                }.padding()
            }
            MeTabView()
            Spacer()
                
        }.navigationBarTitle("", displayMode: .inline)
    }
}

struct FollowBlock: View {
    var count = 0
    var title = ""
    var body: some View {
        VStack(alignment: .center) {
            Text("\(count)")
                .font(.system(size: 14).bold())
                .foregroundColor(.black.opacity(0.8))
            Text(title)
                .font(.system(size: 12))
                .fontWeight(.light)
                .foregroundColor(.gray)
        }.frame(minWidth: 80)
    }
}

struct MeTabView: View {
    @State var currentTab: Int = 0
    @Namespace private var animation
    
    var body: some View {
        ZStack(alignment: .top) {
            TabView(selection: self.$currentTab, content: {
                view1.tag(0)
                view2.tag(1)
                view3.tag(2)
            })
            .tabViewStyle(.page(indexDisplayMode: .never))
            .edgesIgnoringSafeArea(.all)
            navigationBarView
        }
    }
    var navigationitemsIcon: [String] = ["books.vertical", "heart", "lock"]
    
    var navigationBarView: some View {
            HStack {
                ForEach(Array(zip(self.navigationitemsIcon.indices, self.navigationitemsIcon)), id: \.0, content: {
                    index, name in
                    navBarItem(iconName: name, tab: index)
                })
            }
            .background(Color.white)
            .frame(height: 35)
            .edgesIgnoringSafeArea(.top)
            .padding(.bottom, 2)
    }
    
    func navBarItem(iconName: String, tab: Int) -> some View {
        Button {
            self.currentTab = tab
        } label: {
            VStack {
                Spacer()
                Image(systemName: iconName)
                    .foregroundColor(self.currentTab == tab ? Color.black : Color.gray)
                    .frame(maxWidth: .infinity)
                    .padding(.all, 3)
                if self.currentTab == tab {
                    Color.black.frame(width: 60, height: 2)
                        .matchedGeometryEffect(id: "underline", in: animation, properties: .frame)
                } else {
                    Color.clear.frame(width: 60, height: 2)
                }
            }.animation(.spring(), value: currentTab)
        }
        .buttonStyle(.plain)
    }

    var view1: some View {
        Color.orange.opacity(0.2).edgesIgnoringSafeArea(.all)
    }

    var view2: some View {
        Color.blue.opacity(0.2).edgesIgnoringSafeArea(.all)
    }
    
    var view3: some View {
        Color.yellow.opacity(0.2).edgesIgnoringSafeArea(.all)
    }
}

struct MeTabView_Previews: PreviewProvider {
    static var previews: some View {
        MeTabView()
    }
}
