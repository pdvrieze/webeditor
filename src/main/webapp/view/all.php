<?php

$json = [];

$files = glob('*.html');
foreach ($files as $file) {
    $name = str_replace('.html', '', $file);
    $json[$name] = file_get_contents($file);
}

echo json_encode($json, JSON_UNESCAPED_SLASHES);
