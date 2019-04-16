/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysCustomerServiceDetailComponent } from 'app/entities/sys-customer-service/sys-customer-service-detail.component';
import { SysCustomerService } from 'app/shared/model/sys-customer-service.model';

describe('Component Tests', () => {
    describe('SysCustomerService Management Detail Component', () => {
        let comp: SysCustomerServiceDetailComponent;
        let fixture: ComponentFixture<SysCustomerServiceDetailComponent>;
        const route = ({ data: of({ sysCustomerService: new SysCustomerService(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysCustomerServiceDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SysCustomerServiceDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysCustomerServiceDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sysCustomerService).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
