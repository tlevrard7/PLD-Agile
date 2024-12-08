import { Button } from "antd";
import { SettingOutlined, TruckOutlined } from "@ant-design/icons";

interface SidebarProps {
  onTabSelect: (tab: string) => void;
}

export default function Sidebar({ onTabSelect }: SidebarProps) {
  return (
    <div className="p-4 space-y-4">
      {/* Bouton Paramètres */}
      <Button
        block
        icon={<SettingOutlined />}
        className="bg-blue-900 text-white p-2 rounded"
        onClick={() => onTabSelect("settings")}
      >
        Paramètres
      </Button>

      {/* Bouton Gestion des livraisons */}
      <Button
        block
        icon={<TruckOutlined />}
        className="bg-blue-900 text-white p-2 rounded"
        onClick={() => onTabSelect("deliveries")}
      >
        Gestion des livraisons
      </Button>
    </div>
  );
}