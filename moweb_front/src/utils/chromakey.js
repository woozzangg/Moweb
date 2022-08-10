export const chromaKey = function (frame) {
  let l = frame.data.length / 4;

  for (let i = 0; i < l; i++) {
    let r = frame.data[i * 4 + 0];
    let g = frame.data[i * 4 + 1];
    let b = frame.data[i * 4 + 2];
    if (g > 120 && r < 80 && b < 120) {
      // if (r == 0 && g == 158 && b == 43) { // 기준 색상
      frame.data[i * 4 + 3] = 0;
    }
  }

  return frame;
};
