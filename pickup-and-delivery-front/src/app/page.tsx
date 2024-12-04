'use client'

import Test from "@/pages/test";
import { Plan } from "@/model/Plan";
import { createContext, SetStateAction, useState } from "react";
import { CurrentPlan } from "./contexts/current-plan";
import PlanPanel from "@/pages/PlanPanel";
import { Tabs } from "antd";

export const CurrentPlanContext = createContext<CurrentPlan>({ plan: null, setPlan: (value: SetStateAction<Plan | null>) => {} });

export default function Home() {
  const [plan, setPlan] = useState<Plan | null>(null);
  return <CurrentPlanContext.Provider value={{plan, setPlan}}>
    <div className="main-grid">
      <PlanPanel/>
      <Tabs
        defaultActiveKey="1"
        centered
          items={[{
            label: `Tests`,
            key: '1',
            children: <Test/>,
          },{
            label: `Import files`,
            key: '2',
            children: 'Not implemented yet',
          },{
            label: `Manager Deliveries`,
            key: '3',
            children: 'Not implemented yet',
          }]}
      />
    </div>
  </CurrentPlanContext.Provider>
}
