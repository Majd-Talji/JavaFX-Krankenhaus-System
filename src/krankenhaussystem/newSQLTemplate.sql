/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Majd
 * Created: 03.11.2018
 */

/**
ALTER TABLE users
ADD FOREIGN KEY (USER_TYPE)
REFERENCES users_type (ID)
---------------------------------------------------*/
/**
ALTER TABLE users_details 
ADD FOREIGN KEY (USER_ID) 
REFERENCES users (ID)
---------------------------------------------------*/
/**
ALTER TABLE messages 
ADD FOREIGN KEY (PATIENT_ID) 
REFERENCES patient_info (ID)
---------------------------------------------------*/
/**
ALTER TABLE messages 
ADD FOREIGN KEY (FROM_USER) 
REFERENCES users (ID)
---------------------------------------------------*/
/**
ALTER TABLE messages 
ADD FOREIGN KEY (TO_USER) 
REFERENCES users (ID)
---------------------------------------------------*/
/**
ALTER TABLE patient_info
ADD FOREIGN KEY (USER_ID)
REFERENCES users(ID)
---------------------------------------------------*/
/**

---------------------------------------------------*/