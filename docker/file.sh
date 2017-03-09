#!/bin/bash
read f
cat > j
vim j -c 'execute "normal! ih\\\ni\<esc>\<c-a>:wq\<cr>"' > /dev/null && cat j
echo "Your first thing was $f"
