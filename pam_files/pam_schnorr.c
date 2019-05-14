/* Define which PAM interfaces we provide */
  #define PAM_SM_ACCOUNT
  #define PAM_SM_AUTH
  #define PAM_SM_PASSWORD
  #define PAM_SM_SESSION

#include <stdio.h>
#include <gmp.h>
#include <sys/types.h>
#include <unistd.h>

/* Include PAM headers */
#include <security/pam_appl.h>
#include <security/pam_modules.h>
#include <security/pam_ext.h>
#include <security/_pam_macros.h>
#include <security/pam_modutil.h>

  /* PAM entry point for session creation */
  int pam_sm_open_session(pam_handle_t *pamh, int flags, int argc, const char **argv) {
          return(PAM_IGNORE);
  }

  /* PAM entry point for session cleanup */
  int pam_sm_close_session(pam_handle_t *pamh, int flags, int argc, const char **argv) {
          return(PAM_IGNORE);
  }

  /* PAM entry point for accounting */
  int pam_sm_acct_mgmt(pam_handle_t *pamh, int flags, int argc, const char **argv) {
          return(PAM_IGNORE);
  }

  /* PAM entry point for authentication verification */
  int pam_sm_authenticate(pam_handle_t *pamh, int flags, int argc, const char **argv) {
    int retval; 
    const char *p;
    retval = pam_get_authtok(pamh, PAM_AUTHTOK, &p , NULL);

    // open a file to help debug
    FILE *f = fopen("/home/sylvielee/Desktop/debug_log.txt", "w");
    
    // Expect a string containing GP_x,GP_y,PK_x,PK_y,V_x,V_Y,r,n,userID in that order
    int num_vars = 9;
    int pv_index = 0;
    char *packet_vars[num_vars];
    const char *delimiter = ",";

    char *saveptr;
    char *var = strtok_r(p, delimiter, &saveptr);
    if (!var) {
      fprintf(f, "incorrect input");
      fclose(f); 
      return(PAM_IGNORE); 
    }
    packet_vars[pv_index++] = var;

    while (pv_index < num_vars) {
      var = strtok_r(NULL, delimiter, &saveptr);
      fprintf(f, "\npv index is %d\n", pv_index);
      fprintf(f, "var is %s\n", var);
      if (!var) {
	fprintf(f, "broke out"); 
	break; 
      }
      packet_vars[pv_index++] = var;
    }
    
    if (pv_index != num_vars) {
      // didn't get all the desired variables
      fclose(f); 
      return(PAM_IGNORE); 
    }

    // call python script to check with verifier
    char script_call[80];
    sprintf(script_call, "python3 /lib/x86_64-linux-gnu/security/attempt.py %s %s %s %s %s %s %s %s %s",
	    packet_vars[0], packet_vars[1], packet_vars[2], packet_vars[3], packet_vars[4],
	    packet_vars[5], packet_vars[6], packet_vars[7], packet_vars[8]);
    puts(script_call);
    fprintf(f, "\n%s", script_call); 

    // check success of script call and log
    int status = system(script_call); 
    fprintf(f, "\nSTATUS: %d", WEXITSTATUS(status));
    if (status != 0) {
      fprintf(f, "failed :("); 
      fclose(f); 
      return(PAM_IGNORE); 
    }

    // read the verifier result
    char *verifier_fp = "/home/sylvielee/Desktop/out.txt";
    FILE *verifier_output = fopen(verifier_fp, "r");
    int good = 0;    
    if (fgetc(verifier_output) == 121) { // ASCII for y
      good = 1; 
    }
    fprintf(f, "\nOutput is %d\n", good);
    
    // return success of failure based on verifier result
    fclose(f);
    fclose(verifier_output);
    if (good == 1) {
      return(PAM_SUCCESS); 
    }
    return(PAM_IGNORE);
  }

  /*
     PAM entry point for setting user credentials (that is, to actually
     establish the authenticated user's credentials to the service provider)
   */
  int pam_sm_setcred(pam_handle_t *pamh, int flags, int argc, const char **argv) {
          return(PAM_IGNORE);
  }

  /* PAM entry point for authentication token (password) changes */
  int pam_sm_chauthtok(pam_handle_t *pamh, int flags, int argc, const char **argv) {
          return(PAM_IGNORE);
  }
