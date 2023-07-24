import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IServicesante } from '../servicesante.model';

@Component({
  selector: 'jhi-servicesante-detail',
  templateUrl: './servicesante-detail.component.html',
})
export class ServicesanteDetailComponent implements OnInit {
  servicesante: IServicesante | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ servicesante }) => {
      this.servicesante = servicesante;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
