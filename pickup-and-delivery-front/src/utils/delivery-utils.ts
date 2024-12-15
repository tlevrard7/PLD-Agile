const colorMap = new Map<number, string>();

/**
 * Génère une couleur hexadécimale aléatoire.
 */
function generateRandomColor(): string {
  const hue = Math.floor(Math.random() * 360); // Teinte aléatoire
  const saturation = 70 + Math.floor(Math.random() * 30); // Saturation entre 70% et 100%
  const lightness = 50 + Math.floor(Math.random() * 20); // Luminosité entre 50% et 70%
  return `hsl(${hue}, ${saturation}%, ${lightness}%)`;
}

/**
 * Retourne une couleur stable basée sur le pickup.
 * @param pickup - L'identifiant unique de la livraison.
 * @returns Une couleur hexadécimale associée à ce pickup.
 */
export function getStableColor(pickup: number): string {
  if (!colorMap.has(pickup)) {
    const color = generateRandomColor();
    colorMap.set(pickup, color);
  }
  return colorMap.get(pickup)!;
}