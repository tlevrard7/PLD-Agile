'use client'

import Test from "@/pages/test";
import { Plan } from "@/model/Plan";
import { createContext, SetStateAction, useState } from "react";
import { CurrentPlan } from "./contexts/current-plan";
import PlanPanel from "@/pages/PlanPanel";

export const CurrentPlanContext = createContext<CurrentPlan>({ plan: null, setPlan: (value: SetStateAction<Plan | null>) => {} });

export default function Home() {
  const [plan, setPlan] = useState<Plan|null>(null);
  return <>
    <CurrentPlanContext.Provider value={{plan, setPlan}}>
      <PlanPanel/>
      <Test />
    </CurrentPlanContext.Provider>
  </>
}
