import { Plan } from "@/model/Plan";
import { Dispatch, SetStateAction } from "react";

export interface CurrentPlan {
    plan: Plan | null;
    setPlan: Dispatch<SetStateAction<Plan | null>>
}