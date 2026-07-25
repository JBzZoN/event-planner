import { VerificationStatus } from "./verification-status";
export interface UpdateStatus {
    verificationId : number;
    status : VerificationStatus;
    remarks : string;
}
