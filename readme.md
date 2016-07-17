Oauth2 password flow

It demonstrates how to use the password flow with spring boot
The example has two enpoints. "/person" and "/person/me". "/person" allow only 'admin' users and "/person/me" allow any authenticated user.

To get accessToken 

$ curl -POST http://localhost:8080/oauth/token -d grant_type=password -d username=marcelo -d password=123 -d client_id=foo -d client_secret=foosecret

{"access_token":"8694b4e3-3470-4847-a99a-b56aa7c36a53","token_type":"bearer","refresh_token":"1eac57f2-2574-4419-817f-8b3ac840965f","expires_in":43199,"scope":"openid"}


curl -H "Authorization: Bearer 8694b4e3-3470-4847-a99a-b56aa7c36a53" http://localhost:8080/person
curl -H "Authorization: Bearer 8694b4e3-3470-4847-a99a-b56aa7c36a53" http://localhost:8080/person/me

