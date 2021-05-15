import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';

import 'package:flutter_android_tv_channels_example/main.dart';

void main() {
  testWidgets('Verify Platform version', (tester) async {
    await tester.pumpWidget(MyApp());

    expect(
      find.byWidgetPredicate((widget) => widget is Text && widget.data.startsWith('Running on:')),
      findsOneWidget,
    );
  });
}
