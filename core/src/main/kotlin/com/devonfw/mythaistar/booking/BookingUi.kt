package com.devonfw.mythaistar.booking

interface BookingUi{

   fun onInviteFriendsCheckBoxCheckedError()
   fun onInviteFriendsDateTimeError()
   fun onInviteFriendsNameError()
   fun onInviteFriendsEmailError()
   fun onInviteFriendsEmptyError()
   fun onInviteFriendsEmailsError()

   //succes
   fun onInviteFriends()


}