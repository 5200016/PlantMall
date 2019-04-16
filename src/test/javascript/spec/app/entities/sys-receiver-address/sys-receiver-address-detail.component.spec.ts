/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysReceiverAddressDetailComponent } from 'app/entities/sys-receiver-address/sys-receiver-address-detail.component';
import { SysReceiverAddress } from 'app/shared/model/sys-receiver-address.model';

describe('Component Tests', () => {
    describe('SysReceiverAddress Management Detail Component', () => {
        let comp: SysReceiverAddressDetailComponent;
        let fixture: ComponentFixture<SysReceiverAddressDetailComponent>;
        const route = ({ data: of({ sysReceiverAddress: new SysReceiverAddress(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysReceiverAddressDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SysReceiverAddressDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysReceiverAddressDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sysReceiverAddress).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
