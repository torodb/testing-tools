[ ! -f "$ORACLE_BASE/oradata.tar.gz" ] || tar xz  -C "$ORACLE_BASE" -f "$ORACLE_BASE/oradata.tar.gz"
bash "$ORACLE_BASE/$RUN_FILE"