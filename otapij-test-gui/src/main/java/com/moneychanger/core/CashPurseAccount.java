/************************************************************
-----BEGIN PGP SIGNED MESSAGE-----
Hash: SHA1

 *                 M O N E Y C H A N G E R
 *
 *  Open Transactions:
 *       Financial Cryptography and Digital Cash
 *       Library, Protocol, API, Server, CLI, GUI 
 *    
 *       -- Anonymous Numbered Accounts.
 *       -- Untraceable Digital Cash.
 *       -- Triple-Signed Receipts.
 *       -- Cheques, Vouchers, Transfers, Inboxes.
 *       -- Basket Currencies, Markets, Payment Plans.
 *       -- Signed, XML, Ricardian-style Contracts.
 *       -- Scripted smart contracts.
 *    
 *  Copyright (C) 2010-2013 by "Fellow Traveler" (A pseudonym)
 *
 *  EMAIL:
 *  FellowTraveler@rayservers.net --- SEE PGP KEY BELOW.
 *  F3llowTraveler@gmail.com --- (not preferred.)
 *  
 *  FINGERPRINT:
 *  9DD5 90EB 9292 4B48 0484  7910 0308 00ED F951 BB8E
 *
 *  BITCOIN:  1NtTPVVjDsUfDWybS4BwvHpG2pdS9RnYyQ
 *
 *  OFFICIAL PROJECT WIKI:
 *  https://github.com/FellowTraveler/Moneychanger
 *  https://github.com/FellowTraveler/Open-Transactions/wiki
 *
 *  WEBSITE:
 *  http://www.OpenTransactions.org/
 *    
 *  Components and licensing:
 *   -- Moneychanger..A Java client GUI.....LICENSE:.....GPLv3
 *   -- otlib.........A class library.......LICENSE:...LAGPLv3
 *   -- otapi.........A client API..........LICENSE:...LAGPLv3
 *   -- opentxs/ot....Command-line client...LICENSE:...LAGPLv3
 *   -- otserver......Server Application....LICENSE:....AGPLv3
 *  Github.com/FellowTraveler/Open-Transactions/wiki/Components
 *
 *  All of the above OT components were designed and written by
 *  Fellow Traveler, with the exception of Moneychanger, which
 *  was contracted out to Vicky C (bitcointrader4@gmail.com).
 *  The open-source community has since actively contributed.
 *
 *  -----------------------------------------------------
 *
 *   LICENSE:
 *   This program is free software: you can redistribute it
 *   and/or modify it under the terms of the GNU General
 *   Public License as published by the Free Software
 *   Foundation, either version 3 of the License, or (at your
 *   option) any later version.
 *
 *   You should have received a copy of the GNU General
 *   Public License along with this program.  If not, see:
 *   http://www.gnu.org/licenses/
 *
 *   If you would like to use this software outside of the free
 *   software license, please contact FellowTraveler.
 *   (Unfortunately many will run anonymously and untraceably,
 *   so who could really stop them?)
 *   
 *   DISCLAIMER:
 *   This program is distributed in the hope that it will be
 *   useful, but WITHOUT ANY WARRANTY; without even the implied
 *   warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 *   PURPOSE.  See the GNU General Public License for more
 *   details.

-----BEGIN PGP SIGNATURE-----
Version: GnuPG v1.4.9 (Darwin)

iQIcBAEBAgAGBQJRSsfMAAoJEAMIAO35UbuOCTQQAJWUR6l+IbUGYPfudumDBZep
XWu5aUVXPt+HTetWobTT3VrSEoQTV+t3Qk10EHzIIQNIvDNkA3cfQod5xMk3CZgm
MuA1PTDZdmrcm1lf92rVULaiB2tHYuPKYryFfkmNcidoWaJAI0ny/AE4gSdopKuU
MCLhB+fWpPv+nK9Qjb8duvRyFjYeLxrnwjfy05zNIY5Fj4HsCzmf6G6xqbUOZzBA
Zc4RjfKeg9MVJl4ObIKhDfpicCTVZkgFPVGeok/KtmiPRgV1HtaHqib4RiN9VMkr
YKbOUOb931pukRJQv+z5fT1EQkSVBDO5Th2q7Nls5idMgtR2BPXxVhs+e8OM4IJK
W+1V0WHWHuE+6SRKQrPU4hAmXrtmGRtu474TTmPlW6dCqFgvLWBuxeTRCPf4l29T
ImEOxdjFwlMVoxsazE3KE/YMbX7IiZqgLx/C4OTPlz7BVLhphzPRXA7KhhRFi1Df
jie5oRhJ4zbTFQI8SKsjbx0H/4VpB+Vtjx6fOxnLUpjZAE7G6ZL2zOEK8rtiGeiH
0AWK2rKWP8oOMnQwBMP838WRtxFmaOIhvVqAngjynSmVouf1RKKU7y7/YQ0iVDcN
WqAhaZhvszQ6UCDAEi11rfdC2qt29Jds9lS41YGqvYaan+b50lr5u59Uknz2LC94
HS4/gWtdVEVnXDda0wk9
=CbRB
-----END PGP SIGNATURE-----
 **************************************************************/


package com.moneychanger.core;
import com.moneychanger.core.dataobjects.CashPurseDetails;
import com.moneychanger.core.util.OTAPI_Func;
import com.moneychanger.core.util.Helpers;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.opentransactions.otapi.otapi;
import org.opentransactions.otapi.otapiJNI;
import org.opentransactions.otjavalib.util.Utility;

public class CashPurseAccount extends Account {

    public CashPurseAccount() {
        type = "CashPurseAccount";
    }

    @Override
    public boolean createAccount() {
        return false;
    }

    @Override
    public boolean deleteAccount(String accountID) {
        return false;
    }

    @Override
    public void editAccount() {
    }

    public CashPurseDetails getCashPurseDetails(String cashPurseID) {

        Map gridData = new HashMap();

        String[] key = cashPurseID.split(":");

        String serverID = key[0];
        String assetID  = key[1];
        String nymID    = key[2];

        String cashPurse = otapiJNI.OTAPI_Basic_LoadPurse(serverID, assetID, nymID);

        if (!Utility.VerifyStringVal(cashPurse)) {
            System.out.println("Purse is null");
            return null;
        }
        gridData = getGridData(serverID, assetID, nymID, cashPurse);
        CashPurseDetails cashDetails = new CashPurseDetails();
        cashDetails.setPurseGrid(gridData);
        cashDetails.setBalance(otapiJNI.OTAPI_Basic_Purse_GetTotalValue(serverID, assetID, cashPurse));
        cashDetails.setServerID(serverID);
        cashDetails.setAssetID(assetID);
        cashDetails.setNymID(nymID);
        cashDetails.setPurse(cashPurse);
        cashDetails.setServeName(otapiJNI.OTAPI_Basic_GetServer_Name(serverID));
        cashDetails.setAssetType(otapiJNI.OTAPI_Basic_GetAssetType_Name(assetID));

        if (!Utility.VerifyStringVal(nymID)) {
            cashDetails.setNymName("");
        } else {
            cashDetails.setNymName(otapiJNI.OTAPI_Basic_GetNym_Name(nymID));
        }

        return cashDetails;
    }

    public void getCashPurseList(String assetID, String serverID, String nymID) {

        /*accountListOT.put(serverID + ":" + assetID + ":" + nymID, new String[]{"cashp", "100", type, serverID + ":" + assetID + ":" + nymID});
        if (true) {
        return;
        }*/

        int countServer = otapiJNI.OTAPI_Basic_GetServerCount();
        int countAsset = otapiJNI.OTAPI_Basic_GetAssetTypeCount();
        int countNyms = otapiJNI.OTAPI_Basic_GetNymCount();

        final String selectedServerID = serverID;
        final String selectedAssetID = assetID;
        final String selectedNymID = nymID;

        System.out.println("assetID:" + assetID);
        System.out.println("serverID:" + serverID + " countServer " + otapiJNI.OTAPI_Basic_GetServerCount());
        System.out.println("nymID:" + nymID);

        for (int j = 0; j < countServer; j++) {
            if ("ALL".equalsIgnoreCase(selectedServerID)) {
                serverID = otapiJNI.OTAPI_Basic_GetServer_ID(j);
                System.out.println("INNN LOOP serverID:" + serverID);
            } else {
                j = countServer + 1;
            }
            for (int i = 0; i < countAsset; i++) {
                if ("ALL".equalsIgnoreCase(selectedAssetID)) {
                    assetID = otapiJNI.OTAPI_Basic_GetAssetType_ID(i);
                } else {
                    i = countAsset + 1;
                }
                for (int k = 0; k < countNyms; k++) {
                    if ("ALL".equalsIgnoreCase(selectedNymID)) {
                        nymID = otapiJNI.OTAPI_Basic_GetNym_ID(k);
                    } else {
                        k = countNyms + 1;
                    }
                    //String cashPurse = otapiJNI.OTAPI_Basic_LoadPurse(serverID,assetID);
                    System.out.println("qqassetID:" + assetID);
                    System.out.println("aaserverID:" + serverID);
                    System.out.println("ccnymID:" + nymID);

                    if (Utility.VerifyStringVal(assetID) && Utility.VerifyStringVal(serverID) && Utility.VerifyStringVal(nymID) &&
                            otapi.Exists("purse", serverID, nymID, assetID) ) {
                        String cashPurse = otapiJNI.OTAPI_Basic_LoadPurse(serverID, assetID, nymID);
                        System.out.println("cashPurse:" + cashPurse);
                        if (Utility.VerifyStringVal(cashPurse)) {
                            try {
                                key = serverID + ":" + assetID + ":" + nymID;
                                label = otapiJNI.OTAPI_Basic_GetAssetType_Name(assetID);
                                amount = otapiJNI.OTAPI_Basic_Purse_GetTotalValue(serverID, assetID, cashPurse);
                                accountListOT.put(key, new String[]{label, amount, type, key});

                            } catch (Exception nfe) {
                                nfe.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void loadAccount(String assetID, String serverID, String nymID) {
        System.out.println("In CASH load");
        getCashPurseList(assetID, serverID, nymID);
    }

    @Override
    public CashPurseDetails getAccountDetails(String accountID) {
        return getCashPurseDetails(accountID);
    }

    @Override
    public boolean editLabel(String accountID, String newLabel) {
        return false;
    }

    public boolean importCashPurse(String serverID, String nymID, String assetID, String userInput, boolean isPurse) {


        boolean isSuccess = true;
        System.out.println("importCashPurse, serverID:" + serverID + " nymID:" + nymID + " assetID:" + assetID);
        System.out.println("importCashPurse, userInput purse:" + userInput);
        if (!isPurse) {
            System.out.println("importCashPurse, isPurse:" + isPurse);
            String purse = otapiJNI.OTAPI_Basic_CreatePurse(serverID, assetID, nymID, nymID);

            if (!Utility.VerifyStringVal(purse)) {
                System.out.println("OT_API_CreatePurse returned null");
                return false;
            }
            System.out.println("importCashPurse, OT_API_CreatePurse returne :" + purse);

            String newPurse = otapiJNI.OTAPI_Basic_Purse_Push(serverID, assetID, nymID, nymID, purse, userInput);

            if (!Utility.VerifyStringVal(newPurse)) {
                System.out.println("OT_API_Purse_Push returned null");
                return false;
            }
            System.out.println("importCashPurse, OT_API_Purse_Push returne :" + newPurse);
            userInput = newPurse;
        }
        System.out.println("importCashPurse, Before calling OT_API_Wallet_ImportPurse, final purse:" + userInput);
        System.out.println("importCashPurse just before api , serverID:" + serverID + " nymID:" + nymID + " assetID:" + assetID);
        isSuccess = otapiJNI.OTAPI_Basic_Wallet_ImportPurse(serverID, assetID, nymID, userInput);
        return isSuccess;

    }

    private String processCashPurse(String serverID, String assetID, String nymID, String oldPurse, ArrayList selectedTokens, String recepientNymID) {

        String newPurse = null;
        //oldPurse = otapiJNI.OTAPI_Basic_LoadPurse(serverID, assetID, nymID);

        // By this point, we know that "selected tokens" has a size of 0, or MORE THAN ONE. (But NOT 1 exactly.)
        // (At least, if this function was called by exportCashPurse.)


        // This block handles cases where NO TOKENS ARE SELECTED.
        // (Meaning "PROCESS" THEM ALL.)
        //
        if ((selectedTokens == null) || (selectedTokens.size() < 1)) {
            System.out.println("IN Entire Cash Purse");

            // newPurse is created, OWNED BY RECIPIENT.
            //
            newPurse = otapiJNI.OTAPI_Basic_CreatePurse(serverID, assetID, recepientNymID, nymID);

            if (!Utility.VerifyStringVal(newPurse)) {
                System.out.println("IN processCashPurse, OT_API_CreatePurse returned null");
                return null;
            }

            // Iterate through the OLD PURSE. (as tempPurse.)
            //
            int count = otapiJNI.OTAPI_Basic_Purse_Count(serverID, assetID, oldPurse);
            String tempPurse = oldPurse;

            for (int i = 0; i < count; i++) {
                // Peek into TOKEN, from the top token on the stack. (And it's STILL on top after this call.)
                //
                String token = otapiJNI.OTAPI_Basic_Purse_Peek(serverID, assetID, nymID, tempPurse);

                // Now pop the token off of tempPurse (our iterator for the old purse).
                // Store updated copy of purse (sans token) into "str1".
                //
                String str1 = otapiJNI.OTAPI_Basic_Purse_Pop(serverID, assetID, nymID, tempPurse);

                if (!Utility.VerifyStringVal(token) || !Utility.VerifyStringVal(str1)) {
                    System.out.println("IN processCashPurse,OT_API_Purse_Peek or OT_API_Purse_Pop returned null... SHOULD NEVER HAPPEN. Returning null.");
                    return null;
                }

                // Since pop succeeded, copy the output to tempPurse (for next iteration, in case any continue's happen below.)
                // Now tempPurse contains what it did before, MINUS ONE TOKEN. (The exported one.)
                //
                tempPurse = str1;

                // -----------------------

                // Change the OWNER on token, from NymID to RECIPIENT.
                // (In this block, we change ALL the tokens in the purse.)
                //
                String exportedToken = otapiJNI.OTAPI_Basic_Token_ChangeOwner(serverID, assetID, token, nymID, nymID, recepientNymID);

                // If change failed, then continue.
                if (!Utility.VerifyStringVal(exportedToken)) {
                    System.out.println("IN processCashPurse,OT_API_Token_ChangeOwner returned null...(should never happen) Returning null.");
                    return null;
                }

                // PUSH the EXPORTED TOKEN (new owner) into the new purse (again, recipient is new owner) and save results in "str".
                // Results are, FYI, newPurse+exportedToken.
                //
                String str = otapiJNI.OTAPI_Basic_Purse_Push(serverID, assetID, nymID, recepientNymID, newPurse, exportedToken);

                // If push failed, then continue.
                if (!Utility.VerifyStringVal(str)) {
                    System.out.println("IN processCashPurse,OT_API_Purse_Push returned null... (should never happen) Returning null.");
                    return null;
                }

                // Since push succeeded, copy "str" (containing newPurse+exportedToken) into newPurse.
                newPurse = str;
            } // for

            // Save tempPurse to local storage. (For OLD Owner.)
            // By now, all of the tokens have been popped off of this purse, so it is EMPTY.
            // We're now saving the empty purse, since the user exported all of the tokens.
            //
            // THERE MAYBE SHOULD BE AN EXTRA MODAL HERE, that says,
            // "Moneychanger will now save your purse, EMPTY, back to local storage. Are you sure you want to do this?"
            //
            
            if (!otapiJNI.OTAPI_Basic_SavePurse(serverID, assetID, nymID, tempPurse)) // if FAILURE.
            {
                // No error message if saving fails??
                // No modal?
                //
                // FT: adding log.
                System.out.println("IN processCashPurse,IF block OT_API_SavePurse FAILED. SHOULD NEVER HAPPEN!!!!!!");
            }
        } // -------------------------------------------------------------------------------------
        // Else, SPECIFIC TOKENS were selected, so process them...
        else {
            System.out.println("Tokens in Cash Purse being processed");

            // ----------------------------------------------------------
            // newPurseSelectedTokens is created (CORRECTLY) with recepientNymID as owner.
            // newPurseUnSelectedTokens is created (CORRECTLY) with NymID as owner. (Unselected tokens aren't being exported...)
            //
            String newPurseSelectedTokens = otapiJNI.OTAPI_Basic_CreatePurse(serverID, assetID, recepientNymID, nymID);
            String newPurseUnSelectedTokens = otapiJNI.OTAPI_Basic_CreatePurse(serverID, assetID, nymID, nymID);

            if (!Utility.VerifyStringVal(newPurseSelectedTokens) || !Utility.VerifyStringVal(newPurseUnSelectedTokens)) {
                System.out.println("IN processCashPurse,1st or 2nd OT_API_CreatePurse returned null");
                return null;
            }

            // ----------------------------------------------------------
            // Iterate through oldPurse, using tempPurse as iterator.
            //
            int count = otapiJNI.OTAPI_Basic_Purse_Count(serverID, assetID, oldPurse);
            String tempPurse = oldPurse;

            for (int i = 0; i < count; i++) {
                // Peek at the token on top of the stack.
                // (Without removing it.)
                //
                String token = otapiJNI.OTAPI_Basic_Purse_Peek(serverID, assetID, nymID, tempPurse);

                // Remove the top token from the stack, and return the updated stack in "str1".
                //
                String str1 = otapiJNI.OTAPI_Basic_Purse_Pop(serverID, assetID, nymID, tempPurse);

                if (!Utility.VerifyStringVal(str1) || !Utility.VerifyStringVal(token)) {
                    System.out.println("IN processCashPurse,OT_API_Purse_Peek or OT_API_Purse_Pop returned null... returning Null. (SHOULD NEVER HAPPEN.)");
                    return null;
                }

                // Putting updated purse into iterator, so any subsequent "continue"s will work properly.
                //
                tempPurse = str1;

                // ----------------------------------------

                // Grab the TokenID for that token. (Token still has OLD OWNER.)
                //
                String tokenID = otapiJNI.OTAPI_Basic_Token_GetID(serverID, assetID, token);

                if (!Utility.VerifyStringVal(tokenID)) {
                    System.out.println("IN processCashPurse, OT_API_Token_GetID returned null... SHOULD NEVER HAPPEN. Returning now.");
                    return null;
                }

                // ----------------------------------------

                // At this point, we check TokenID (identifying the current token) to see if it's on the SELECTED LIST.
                //
                if (selectedTokens.contains(tokenID)) // We ARE exporting this token. (Its ID was on the list.)
                {
                    // CHANGE OWNER from NYM to RECIPIENT
                    // "token" now contains EXPORTED TOKEN, with NEW OWNER.
                    //
                    String exportedToken = otapiJNI.OTAPI_Basic_Token_ChangeOwner(serverID, assetID, token, nymID, nymID, recepientNymID);

                    if (!Utility.VerifyStringVal(exportedToken)) {
                        System.out.println("IN processCashPurse, OT_API_Token_GetID or OT_API_Token_ChangeOwner returned null... SHOULD NEVER HAPPEN. Returning now.");
                        return null;
                    }

                    // Thus, push exported version of token into new purse for recipient (for selected tokens.)
                    //
                    String str = otapiJNI.OTAPI_Basic_Purse_Push(serverID, assetID, nymID, recepientNymID, newPurseSelectedTokens, exportedToken);

                    if (!Utility.VerifyStringVal(str)) {
                        System.out.println("IN processCashPurse,OT_API_Purse_Push newPurseSelectedTokens returned null... SHOULD NEVER HAPPEN (returning.)");
                        return null;
                    }
                    newPurseSelectedTokens = str;
                } else // The token, this iteration, is NOT being exported, but is remaining with the original owner.
                {
                    String str = otapiJNI.OTAPI_Basic_Purse_Push(serverID, assetID, nymID, nymID, newPurseUnSelectedTokens, token);

                    if (!Utility.VerifyStringVal(str)) {
                        System.out.println("IN processCashPurse,OT_API_Purse_Push newPurseUnSelectedTokens returned null... SHOULD NEVER HAPPEN. Returning null.");
                        return null;
                    }

                    newPurseUnSelectedTokens = str;
                }
            } // for

            // We SAVE newPurseUnSelectedTokens... These remain as the Nym's purse, in local storage.
            //
            if (!otapiJNI.OTAPI_Basic_SavePurse(serverID, assetID, nymID, newPurseUnSelectedTokens)) // if FAILURE.
            {
                // No error message if saving fails??
                // No modal?
                //
                // FT: adding log.
                System.out.println("IN processCashPurse, OT_API_SavePurse FAILED. SHOULD NEVER HAPPEN!!!!!!");
            }

            // The SELECTED ones (with Recipient as owner of purse AND tokens within) are returned as the "newPurse".
            //
            newPurse = newPurseSelectedTokens;
        }

        return newPurse;
    }

    // Input: server ID, assetID, Nym of current owner, existing purse, list of selected tokens, Nym of Recipient, and bool isPasted.
    // Returns: "new Purse"
    //
    public String exportCashPurse(String serverID, String assetID, String nymID, String oldPurse, ArrayList selectedTokens, String recepientNymID, boolean isPasted) {
        System.out.println("exportCashPurse starts, selectedTokens:" + selectedTokens);
        Helpers.setObj(null);

        // If no recipient, then recipient == Nym.
        //
        if (!Utility.VerifyStringVal(recepientNymID) || recepientNymID.length() == 0) {
            System.out.println("exportCashPurse: recepientNym empty--using NymID for recipient instead: " + nymID);
            recepientNymID = nymID;
        }

        // if "isPasted" AND recipientNymID IS NOT EQUAL TO NymID,
        // Question: meaning of isPasted ?
        //
        if (isPasted && !recepientNymID.equals(nymID)) {
            String recepientPubKey = otapiJNI.OTAPI_Basic_LoadPubkey_Encryption(recepientNymID);
            System.out.println("recepientPubKey:" + recepientPubKey);

            // This whole block is all just about loading the pubkey for the recipient, (if I don't already have it.)
            //
            if (!Utility.VerifyStringVal(recepientPubKey)) {
                // ----------------------------------------------------------
                OTAPI_Func  theRequest   = new OTAPI_Func(OTAPI_Func.FT.CHECK_USER, serverID, nymID, recepientNymID);
                String      strResponse  = OTAPI_Func.SendRequest(theRequest, "CHECK_USER");

                if (!Utility.VerifyStringVal(strResponse))
                {
                    System.out.println("IN exportCashPurse: OTAPI_Func.SendRequest() failed. (I give up.) ");
                    return null;
                }
                // ----------------------------------------------------------
                
                recepientPubKey = otapiJNI.OTAPI_Basic_LoadPubkey_Encryption(recepientNymID);                
            }

            // Still?
            if (!Utility.VerifyStringVal(recepientPubKey)) {
                System.out.println("exportCashPurse: recepientPubKey is null");
                return null;
            }
        }
        // --------------------------------------------------

        // By this point, we have verified that we can load the public key for the recipient.
        // (At least, as long as "isPasted" is true.)

        String token         = null;
        String exportedToken = null;

        // If a single token is selected, then execute this block.
        // Otherwise, go below, and processCashPurse.
        //
        if (selectedTokens.size() == 1) {
            // New Purse is created with Nym as the owner (NOT!!!!!! recipient.)
            //
            String newPurse = otapiJNI.OTAPI_Basic_CreatePurse(serverID, assetID, nymID, nymID);

            if (!Utility.VerifyStringVal(newPurse)) {
                System.out.println("OT_API_CreatePurse returned null");
                return null;
            }

            // "int count" starts out containing the count of the tokens in OLD PURSE.
            //
            int count = otapiJNI.OTAPI_Basic_Purse_Count(serverID, assetID, oldPurse);

            String tempPurse = oldPurse; // tempPurse now contains a COPY of OLD PURSE. (From old owner...)

            for (int i = 0; i < count; i++) // iterate through tempPurse, all the tokens.
            {
                // "token" now contains the token for this iteration.
                token = otapiJNI.OTAPI_Basic_Purse_Peek(serverID, assetID, nymID, tempPurse); // DOESN'T POP!! Only peeks.

                // Here we explicitly "pop" as well, so we are positioned in case of any "continue"s.
                //
                String returnStringVal = otapiJNI.OTAPI_Basic_Purse_Pop(serverID, assetID, nymID, tempPurse);

                if (!Utility.VerifyStringVal(token) || !Utility.VerifyStringVal(returnStringVal)) // this should never happen
                {
                    System.out.println("IN export cash, OT_API_Purse_Peek returned null... (should never happen.) ");
                    return null;
                }

                // tempPurse now contains its former contents, MINUS the token being EXPORTED.
                //
                tempPurse = returnStringVal;

                // -----------------------------------

                // We read the token's ID, so we can see if it is on the list of SELECTED tokens (to be exported...)
                String tokenID = otapiJNI.OTAPI_Basic_Token_GetID(serverID, assetID, token);

                // If the Token was selected for export, CHANGE OWNER from NYM to RECIPIENT.
                // "exportedToken" will contain the output version.
                //
                if (selectedTokens.contains(tokenID)) {
                    exportedToken = otapiJNI.OTAPI_Basic_Token_ChangeOwner(serverID, assetID, token, nymID, nymID, recepientNymID);
                    // Normally I'd "break" here, since we were only looking to export a single token.
                    // However, I still need to iterate the rest of the tokens onto the NEW PURSE, and then SAVE IT.
                    // That's why I don't break here, and instead allow the loop to continue.
                } // If the token was NOT selected for export, just push it onto the new purse (for OLD owner, since he still owns it.)
                else // This will happen with ALL TOKENS except ONE.
                {
                    returnStringVal = otapiJNI.OTAPI_Basic_Purse_Push(serverID, assetID, nymID, nymID, newPurse, token);
                    
                    if (!Utility.VerifyStringVal(returnStringVal)) // this should never happen
                    {
                        System.out.println("IN export cash, OT_API_Purse_Push returned null... (should never happen.) Returning null.");
                        return null;
                    }
                    newPurse = returnStringVal;
                }
            }

            // By this point, we looped through all tokens in tempPurse (containing a copy of oldPurse), and we
            // removed the ONE token being exported, and we called ChangeOwner for that token, and as we looped,
            // we added all the other tokens (NOT being exported) to "newPurse".

            // Therefore now we save newPurse, which overwrites the oldPurse that was previously stored there,
            // and contains the remaining, non-exported tokens of the old owner.
            //
            if (!otapiJNI.OTAPI_Basic_SavePurse(serverID, assetID, nymID, newPurse)) {
                // If SAVE FAILS (==0) then set Utility obj to OLD PURSE.
                Helpers.setObj(oldPurse);   // Displays on screen??
                return null;
            }

            // This is either null, or contains the "change owner"d (exported) token.
            //
            return exportedToken; // This means a token is sometimes returned (here), and sometimes a purse (below.)
        }

        // By this point, we know that "selected tokens" has a size of 0, or MORE THAN ONE.

        // Next I create another "newPurse" by calling this function.
        // I pass it the server, asset, NYM ID, OLD PURSE, the selected tokens, and the RECIPIENT NYM.
        //
        String newPurse = processCashPurse(serverID, assetID, nymID, oldPurse, selectedTokens, recepientNymID);

        // Whatever is returned from that function, I return here also. Presumably a purse...
        //
        return newPurse;
    }

    public boolean depositCashPurse(String serverID, String assetID, String nymID, String oldPurse, ArrayList selectedTokens, String accountID) {

        Helpers.setObj(null);
        System.out.println("depositCashPurse starts, selectedTokens:" + selectedTokens);
        String recepientNymID = otapiJNI.OTAPI_Basic_GetAccountWallet_NymID(accountID);
        String newPurse = processCashPurse(serverID, assetID, nymID, oldPurse, selectedTokens, recepientNymID);

        if (!Utility.VerifyStringVal(newPurse)) {
            System.out.println("Before server OT_API_exchangePurse call, new Purse is emtpty.. returning false ");
            return false;
        }
        // ----------------------------------------
        OTAPI_Func  theRequest   = new OTAPI_Func(OTAPI_Func.FT.DEPOSIT_CASH, serverID, recepientNymID, accountID, newPurse);
        String      strResponse  = OTAPI_Func.SendTransaction(theRequest, "DEPOSIT_CASH"); // <========================
        
        if (!Utility.VerifyStringVal(strResponse))
        {
            System.out.println("IN depositCashPurse: OTAPI_Func.SendTransaction(() failed. (I give up.) ");
            // -------------------
            boolean importStatus = otapiJNI.OTAPI_Basic_Wallet_ImportPurse(serverID, assetID, recepientNymID, newPurse);
            System.out.println("Since failure in depositCashPurse, OT_API_Wallet_ImportPurse called, status of import:" + importStatus);
            if (!importStatus) {
                Helpers.setObj(newPurse);
            }

            return false;
        }
        // ---------------------------------------        
        System.out.println("depositCashPurse ends, status: success.");
        
        return true;
    }

    public boolean exchangeCashPurse(String serverID, String assetID, String nymID, String oldPurse, ArrayList selectedTokens) throws InterruptedException {

        Helpers.setObj(null);
        System.out.println(" Cash Purse exchange starts, selectedTokens:" + selectedTokens);
        String newPurse = processCashPurse(serverID, assetID, nymID, oldPurse, selectedTokens, nymID);

        if (!Utility.VerifyStringVal(newPurse)) {
            System.out.println("exchangeCashPurse: Before server OT_API_exchangePurse call, new Purse is empty.. returning false ");
            return false;
        }
        // ------------------------
        OTAPI_Func  theRequest   = new OTAPI_Func(OTAPI_Func.FT.EXCHANGE_CASH, serverID, nymID, assetID, newPurse);
        String      strResponse  = OTAPI_Func.SendTransaction(theRequest, "EXCHANGE_CASH"); // <========================
        
        if (!Utility.VerifyStringVal(strResponse))
        {
            System.out.println("IN exchangeCashPurse: OTAPI_Func.SendTransaction(() failed. (I give up.) ");
            // -------------------
            boolean importStatus = otapiJNI.OTAPI_Basic_Wallet_ImportPurse(serverID, assetID, nymID, newPurse);
            System.out.println("Since failure in exchangeCashPurse, OT_API_Wallet_ImportPurse called, status of import:" + importStatus);
            if (!importStatus) {
                Helpers.setObj(newPurse);
            }

            return false;
        }
        // ---------------------------------------        
        System.out.println("exchangeCashPurse ends, status: success.");
        
        return true;
     }

    private Map getGridData(String serverID, String assetID, String nymID, String purse) {

        Map gridMap = new HashMap();

        /*gridMap.put("tokenk1", new String[]{"denomi","validfrom","validto","caidd","series"});
        gridMap.put("tokenk2", new String[]{"denomi1","avalidfrom2","validto3","bidd4","series5"});
        gridMap.put("tokenk3", new String[]{"denomi2","validfrom2","bvalidto3","cidd4","series5"});
        if(true)
        return gridMap;*/

        int count = otapiJNI.OTAPI_Basic_Purse_Count(serverID, assetID, purse);
        Date d = null;
        long seconds = 0;
        for (int i = 0; i < count; i++) {
            String row[] = new String[6];
            String token = otapiJNI.OTAPI_Basic_Purse_Peek(serverID, assetID, nymID, purse);

            if (Utility.VerifyStringVal(token)) {
                row[3] = otapiJNI.OTAPI_Basic_Token_GetID(serverID, assetID, token);
                row[0] = otapiJNI.OTAPI_Basic_Token_GetDenomination(serverID, assetID, token);
                row[4] = String.valueOf(otapiJNI.OTAPI_Basic_Token_GetSeries(serverID, assetID, token));
                // TODO: convert beow to date
                seconds = Long.parseLong(otapiJNI.OTAPI_Basic_Token_GetValidFrom(serverID, assetID, token));
                d = new Date(seconds * 1000);
                row[1] = String.valueOf(d);
                seconds = Long.parseLong(otapiJNI.OTAPI_Basic_Token_GetValidTo(serverID, assetID, token));
                d = new Date(seconds * 1000);
                row[2] = String.valueOf(d);
                d = null;
                gridMap.put(token, row);
            }
            purse = otapiJNI.OTAPI_Basic_Purse_Pop(serverID, assetID, nymID, purse);
        }
        return gridMap;
    }

    public static void main(String a[]) {
        String ll = "1300706232000";
        long milliseconds = Long.parseLong(ll);
        Date d = new Date(milliseconds);
        System.out.println(String.valueOf(d));

        Date d1 = new Date(System.currentTimeMillis());
        System.out.println(String.valueOf(d1) + " System.currentTimeMillis():" + System.currentTimeMillis());

    }

    public Map refreshGridData(String serverID, String assetID, String nymID) {
        String purse = otapiJNI.OTAPI_Basic_LoadPurse(serverID, assetID, nymID);
        if (!Utility.VerifyStringVal(purse)) {
            return null;
        }
        return getGridData(serverID, assetID, nymID, purse);
    }
}
