import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMetabolique } from '../metabolique.model';

@Component({
  selector: 'jhi-metabolique-detail',
  templateUrl: './metabolique-detail.component.html',
})
export class MetaboliqueDetailComponent implements OnInit {
  metabolique: IMetabolique | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ metabolique }) => {
      this.metabolique = metabolique;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
