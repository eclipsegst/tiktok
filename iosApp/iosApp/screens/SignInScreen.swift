//
//  SignInScreen.swift
//  iosApp
//
//  Created by Zhaolong Zhong on 4/23/22.
//

import SwiftUI
import shared

struct SignInScreen: View {
    
    @State var email = ""
    @State var password = ""
    @State var passwordVisible = false
    
    let borderColor = Color.blue
        
    var body: some View{
        VStack(){
            Text("App Name")
                .font(.title)
                .fontWeight(.bold)
                .padding(.bottom, 200)
            
            TextField("Username or Email",text:self.$email)
                .autocapitalization(.none)
                .padding()
                .background(RoundedRectangle(cornerRadius:6).stroke(borderColor, lineWidth: 2))
                .padding(.top, 0)
            
            HStack(spacing: 10){
                VStack{
                    if self.passwordVisible {
                        TextField("Password", text: self.$password)
                            .autocapitalization(.none)
                    } else {
                        SecureField("Password", text: self.$password)
                            .autocapitalization(.none)
                    }
                }
                
                Button(action: {
                    self.passwordVisible.toggle()
                }) {
                    Image(systemName: self.passwordVisible ? "eye.slash.fill" : "eye.fill")
                        .foregroundColor(Color.black.opacity(0.7))
                        .opacity(0.6)
                }
            }
            .padding()
            .background(RoundedRectangle(cornerRadius: 6).stroke(borderColor, lineWidth: 2))
            .padding(.top, 10)
            
            HStack{
                Spacer()
                Button(action: {
                    self.resetPassword()
                }) {
                    Text("Forget Password")
                        .fontWeight(.medium)
                        .foregroundColor(Color.blue)
                }.padding(.top, 10.0)
            }
            
            Button(action: {
                self.signIn()
            }) {
                Text("Sign in")
                    .foregroundColor(.white)
                    .fontWeight(.bold)
                    .padding(.vertical)
                 .frame(width: UIScreen.main.bounds.width - 50)
            }
            .background(Color.blue)
            .cornerRadius(6)
            .padding(.top, 10)
            
            VStack{
                Text("Don't have an account ?")
                
                NavigationLink(destination: SignUpScreen()){
                    Text("Sign up")
                    .fontWeight(.bold)
                    .foregroundColor(Color.blue)
                    .padding(5)
                }
                
            }.padding(.top, 15)
        }
        .padding(.horizontal, 25)
    }
    
    func signIn() {
        print("sign in with email: \(email)")
    }
            
    func resetPassword() {
        print("Forget Password clicked")
    }
}

struct SignInScreen_Previews: PreviewProvider {
    static var previews: some View {
        if #available(iOS 15.0, *) {
            SignInScreen()
                .previewInterfaceOrientation(.portrait)
        } else {
            // Fallback on earlier versions
        }
    }
}
