**Requirements:**

- User should be able to Resister
- User should be able to Add Contact
- User should be able to Block/Unblock Contact/Numbers
- User should be able to Report Spam
- User should be able to Identify a Caller when Call comes
- User Service
  - Handle User Registration
  - Business Registration
- Contact manager - Manages User Contacts


**User:**
- userName
- userPhoneNumber
- userEmail
- User Type
- List<Contacts> blockedContacts
- registerUser()

**Contact:**
- Phone Number
- Contact Type
- SpamCount
- addContact()
- blockContact()
- unblockContact()
- reportSpam()
- identifyCaller()
- blackListContact()

**Contact Type:**
- Normal
- Business 
- Black Listed 
- Spam

**User type:**
- Normal user
- Contact Manager