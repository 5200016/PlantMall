/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysOrderDetailComponent } from 'app/entities/sys-order/sys-order-detail.component';
import { SysOrder } from 'app/shared/model/sys-order.model';

describe('Component Tests', () => {
    describe('SysOrder Management Detail Component', () => {
        let comp: SysOrderDetailComponent;
        let fixture: ComponentFixture<SysOrderDetailComponent>;
        const route = ({ data: of({ sysOrder: new SysOrder(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysOrderDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SysOrderDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysOrderDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sysOrder).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
